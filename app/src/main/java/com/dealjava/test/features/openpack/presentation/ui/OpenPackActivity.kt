package com.dealjava.test.features.openpack.presentation.ui

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import com.dealjava.test.core.models.IngredientModel
import com.dealjava.test.R
import com.dealjava.test.core.constants.IngredientConstants
import com.dealjava.test.core.constants.RecipeConstants
import com.dealjava.test.features.openpack.presentation.viewmodels.OpenPackViewModel
import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel
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

    private lateinit var clCombine: ConstraintLayout
    private lateinit var clCard: ConstraintLayout
    private lateinit var rlOpenPack: RelativeLayout
    private lateinit var lottieAnimationViewCombineToRight: LottieAnimationView
    private lateinit var lottieAnimationViewCombineToLeft: LottieAnimationView
    private lateinit var lottieAnimationViewCombineResult: LottieAnimationView
    private lateinit var lottieAnimationViewOpenPack: LottieAnimationView
    private lateinit var btnOkCombine: Button

    val context = this
    private var isOpenPack: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        clCombine = findViewById<ConstraintLayout>(R.id.clCombine)
        clCard = findViewById<ConstraintLayout>(R.id.clCard)
        rlOpenPack = findViewById<RelativeLayout>(R.id.rlOpenPack)
        lottieAnimationViewCombineToRight = findViewById<LottieAnimationView>(R.id.lottieAnimationViewCombineToRight)
        lottieAnimationViewCombineToLeft = findViewById<LottieAnimationView>(R.id.lottieAnimationViewCombineToLeft)
        lottieAnimationViewCombineResult = findViewById<LottieAnimationView>(R.id.lottieAnimationViewCombine1Result)
        lottieAnimationViewOpenPack = findViewById<LottieAnimationView>(R.id.lottieAnimationViewOpenPack)
        btnOkCombine = findViewById(R.id.btnOk)

        val lottieAnimationView1 = findViewById<LottieAnimationView>(R.id.lottieAnimationView1)
        val lottieAnimationView2 = findViewById<LottieAnimationView>(R.id.lottieAnimationView2)
        val lottieAnimationView3 = findViewById<LottieAnimationView>(R.id.lottieAnimationView3)
        val lottieAnimationView4 = findViewById<LottieAnimationView>(R.id.lottieAnimationView4)
        val lottieAnimationView5 = findViewById<LottieAnimationView>(R.id.lottieAnimationView5)
        lottieAnimations = listOf(lottieAnimationView1, lottieAnimationView2, lottieAnimationView3, lottieAnimationView4, lottieAnimationView5)

        val btnOpenPack = findViewById<Button>(R.id.btnOpenPackAgain)
        val btnListUnlockRecipes = findViewById<Button>(R.id.btnListUnlockRecipes)

        btnOkCombine.setOnClickListener{
            clCombine.visibility = View.GONE
        }

        btnOpenPack.setOnClickListener{
            rlOpenPack.visibility = View.VISIBLE
            lottieAnimationViewOpenPack.playAnimation()
            lottieAnimationViewOpenPack.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    rlOpenPack.visibility = View.GONE
                    clCard.visibility = View.VISIBLE
                    initCard()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
//            for ((index, _) in lottieAnimations.withIndex()){
//                lottieAnimations[index].visibility = View.VISIBLE
//            }
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


    }

    private fun initCard(){
        for ((index, _) in lottieAnimations.withIndex()) {
            lottieAnimations[index].setAnimation(listIngredient[index].lottieSrc)
            lottieAnimations[index].setRepeatCount(0);
            lottieAnimations[index].playAnimation()
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
                        view.animate()
                            .x(startX)
                            .y(startY)
                            .setDuration(200)
                            .start()
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
                                    val recipe =
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
                                        showRlCombine(ingredientDrop, ingredientDrag, recipe)
                                    } else {
                                        viewModel.saveRecipe(Pair(
                                            ingredientDrag.name,
                                            ingredientDrop.name
                                        ))
                                        showRlCombine(ingredientDrag, ingredientDrop, recipe)
                                    }
//                                    showRecipeDialog(recipeName ?: "")
                                } else {
                                    showRlCombine(ingredientDrag, ingredientDrop, null){
                                        lottieAnimations[indexDrag].visibility = View.INVISIBLE
                                        lottieAnimations[indexDropped].visibility = View.INVISIBLE
                                    }
                                }
                            }
                        }
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

    private fun showRecipeDialog(recipeName: String) {
        AlertDialog.Builder(this)
            .setTitle("Resep Terbentuk!")
            .setMessage("Kombinasi ini menghasilkan: $recipeName")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun showRlCombine(
        ingredientModel1: IngredientModel,
        ingredientModel2: IngredientModel,
        recipe: RecipeModel? = null,
        onDone: () -> Unit = {}
    ){
        isLottie1Loaded = false
        isLottie2Loaded = false
        clCombine.visibility = View.VISIBLE
        lottieAnimationViewCombineResult.visibility = View.GONE
        lottieAnimationViewCombineToRight.setAnimation(ingredientModel1.lottieCombineToRightSrc)
        lottieAnimationViewCombineToLeft.setAnimation(ingredientModel2.lottieCombineToLeftSrc)

        lottieAnimationViewCombineToRight.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                lottieAnimationViewCombineResult.visibility = View.VISIBLE
                if (recipe != null){
                    lottieAnimationViewCombineResult.setAnimation(recipe.lottieSrc)
                } else {
                    lottieAnimationViewCombineResult.setAnimation(R.raw.failed_lottie)
                }
                lottieAnimationViewCombineResult.playAnimation()
                onDone()
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })


        LottieCompositionFactory.fromRawRes(context, ingredientModel1.lottieCombineToRightSrc).addListener { composition ->
            lottieAnimationViewCombineToRight.setComposition(composition)
            isLottie1Loaded = true
            checkAndPlayAnimations()
        }

        LottieCompositionFactory.fromRawRes(context, ingredientModel2.lottieCombineToLeftSrc).addListener { composition ->
            lottieAnimationViewCombineToLeft.setComposition(composition)
            isLottie2Loaded = true
            checkAndPlayAnimations()
        }


    }

    var isLottie1Loaded = false
    var isLottie2Loaded = false

    private fun checkAndPlayAnimations() {
        if (isLottie1Loaded && isLottie2Loaded) {
            lottieAnimationViewCombineToRight.playAnimation()
            lottieAnimationViewCombineToLeft.playAnimation()
        }
    }
}