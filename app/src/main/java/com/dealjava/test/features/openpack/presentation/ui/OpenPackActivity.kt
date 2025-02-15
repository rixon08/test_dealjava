package com.dealjava.test.features.openpack.presentation.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.dealjava.test.core.models.IngredientModel
import com.dealjava.test.R
import com.dealjava.test.core.constants.IngredientConstants
import com.dealjava.test.core.constants.RecipeConstants
import com.dealjava.test.features.openpack.presentation.viewmodels.OpenPackViewModel
import com.dealjava.test.features.unlockrecipe.presentation.ui.UnlockRecipeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenPackActivity : AppCompatActivity() {
    private var startX = 0f
    private var startY = 0f
    private var dX = 0f
    private var dY = 0f

    private lateinit var lottieAnimations: List<LottieAnimationView>

    private lateinit var selectedLottie: LottieAnimationView

    private lateinit var listIngredient: List<IngredientModel>

    private val viewModel: OpenPackViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lottieAnimationView1 = findViewById<LottieAnimationView>(R.id.lottieAnimationView1)
        val lottieAnimationView2 = findViewById<LottieAnimationView>(R.id.lottieAnimationView2)
        val lottieAnimationView3 = findViewById<LottieAnimationView>(R.id.lottieAnimationView3)
        val lottieAnimationView4 = findViewById<LottieAnimationView>(R.id.lottieAnimationView4)
        val lottieAnimationView5 = findViewById<LottieAnimationView>(R.id.lottieAnimationView5)
        lottieAnimations = listOf(lottieAnimationView1, lottieAnimationView2, lottieAnimationView3, lottieAnimationView4, lottieAnimationView5)

        val btnOpenPack = findViewById<Button>(R.id.btnOpenPackAgain)
        val btnListUnlockRecipes = findViewById<Button>(R.id.btnListUnlockRecipes)

        btnOpenPack.setOnClickListener{
            for ((index, _) in lottieAnimations.withIndex()){
                lottieAnimations[index].visibility = View.VISIBLE
            }
        }

        btnListUnlockRecipes.setOnClickListener{
            val intent = Intent(this, UnlockRecipeActivity::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity() // Keluar dari semua activity
            }
        })


        listIngredient = IngredientConstants.listIngredient.shuffled()

        for ((index, _) in lottieAnimations.withIndex()) {
            lottieAnimations[index].setAnimation(listIngredient[index].lottieSrc)
            lottieAnimations[index].tag = index
            lottieAnimations[index].setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selectedLottie = view as LottieAnimationView
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY

                        startX = view.x
                        startY = view.y
                        selectedLottie.z = 100f
                        for (lottie in lottieAnimations){
                            if (lottie != selectedLottie){
                                lottie.z = 0f
                            }
                        }
                    }
                    MotionEvent.ACTION_MOVE -> {
                        view.animate()
                            .x(event.rawX + dX)
                            .y(event.rawY + dY)
                            .setDuration(0)
                            .start()
                    }
                    MotionEvent.ACTION_UP -> {
                        val droppedOn = detectDroppedTarget(view as LottieAnimationView)
                        if (droppedOn != null && droppedOn != selectedLottie) {
                            Log.d("Drop", "${resources.getResourceEntryName(view.id)} dropped on ${resources.getResourceEntryName(droppedOn.id)}")
                            val indexDrag = view.tag as Int
                            val indexDropped = droppedOn.tag as Int

                            val ingredientDrag = listIngredient[indexDrag]
                            val ingredientDrop = listIngredient[indexDropped]
                            if (lottieAnimations[indexDropped].visibility == View.VISIBLE) {
                                if (RecipeConstants.recipes.containsKey(
                                        Pair(
                                            ingredientDrag.name,
                                            ingredientDrop.name
                                        )
                                    ) ||
                                    RecipeConstants.recipes.containsKey(
                                        Pair(
                                            ingredientDrop.name,
                                            ingredientDrag.name
                                        )
                                    )
                                ) {
                                    val recipeName =
                                        RecipeConstants.recipes[Pair(ingredientDrag.name, ingredientDrop.name)]
                                            ?: RecipeConstants.recipes[Pair(
                                                ingredientDrop.name,
                                                ingredientDrag.name
                                            )]
                                    if (RecipeConstants.recipes.containsKey(
                                            Pair(
                                                ingredientDrop.name,
                                                ingredientDrag.name
                                            )
                                        )){
                                        viewModel.saveRecipe(Pair(
                                            ingredientDrop.name,
                                            ingredientDrag.name
                                        ))
                                    } else {
                                        viewModel.saveRecipe(Pair(
                                            ingredientDrag.name,
                                            ingredientDrop.name
                                        ))
                                    }
                                    showRecipeDialog(recipeName ?: "")
                                } else {
//                                (lottieAnimations[indexDrag].parent as? ViewGroup)?.removeView(lottieAnimations[indexDrag])
//                                (lottieAnimations[indexDropped].parent as? ViewGroup)?.removeView(lottieAnimations[indexDropped])
                                    lottieAnimations[indexDrag].visibility = View.INVISIBLE
                                    lottieAnimations[indexDropped].visibility = View.INVISIBLE
                                }
                            }
                            // Pindahkan ke tengah target
//                            val centerX = droppedOn.x + (droppedOn.width - view.width) / 2
//                            val centerY = droppedOn.y + (droppedOn.height - view.height) / 2
//                            view.animate()
//                                .x(centerX)
//                                .y(centerY)
//                                .setDuration(200)
//                                .start()
                        }
//                        else {
                            view.animate()
                                .x(startX)
                                .y(startY)
                                .setDuration(200)
                                .start()
//                        }
                    }
                }
                true
            }
        }
    }

    private fun detectDroppedTarget(view: LottieAnimationView): LottieAnimationView? {
        val rectA = Rect()
        view.getHitRect(rectA)

        for (target in lottieAnimations) {
            if (target != view) {
                val rectTarget = Rect()
                target.getHitRect(rectTarget)
                if (rectA.intersect(rectTarget)) {
                    return target
                }
            }
        }
        return null
    }

    private fun isViewOverlapping(view1: View, view2: View): Boolean {
        val rect1 = Rect()
        val rect2 = Rect()
        view1.getHitRect(rect1)
        view2.getHitRect(rect2)
        return rect1.intersect(rect2)
    }

    private fun showRecipeDialog(recipeName: String) {
        AlertDialog.Builder(this)
            .setTitle("Resep Terbentuk!")
            .setMessage("Kombinasi ini menghasilkan: $recipeName")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}