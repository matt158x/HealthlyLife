package com.example.healthlylife.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.healthlylife.R
import com.example.healthlylife.data.RecipeData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailScreenViewModel @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _recipes = mutableStateOf(
        mapOf(
            R.drawable.granolafruits1 to RecipeData(
                "Granola Fruits",
                listOf("1 cup flaked coconut","1/2 cup chopped almonds","1/2 cup chopped pecans","1/3 cup pepitas","2 tablespoons flaxseed meal","1 teaspoon cinnamon","1 teaspoon sea salt","1/2 cup melted coconut oil","1/2 cup pure maple syrup","1 tablespoon pure vanilla extract","1 cup freeze-dried strawberries","3/4 cup white chocolate chips"),
                "1. Preheat oven to 325 degrees F. Line a large-rimmed baking sheet with parchment paper and set aside.\n\n 2. In a large bowl, combine the oats, coconut, almonds, pecans, pepitas, flaxseed, cinnamon, and sea salt.\n\n 3. Add the melted coconut oil, maple syrup, and vanilla extract. Stir until mixture is well combined.\n\n4. Transfer the mixture to the prepared baking sheet and spread in an even layer. Use a spatula or the bottom of a measuring cup to press the granola down in an even layer.\n\n5. Bake for 25 to 30 minutes or until granola is slightly golden brown. Do not stir, but make sure you rotate the pan halfway through the baking time.\n\n6. Remove the pan from the oven and let the granola cool completely on the pan without stirring.\n\n7. When the granola is completely cooled, break into clusters and stir in the freeze-dried strawberries and white chocolate chips, if using. Enjoy!",
                listOf(R.drawable.full_grannola1),
                proteins = 9, carbs = 77, fat = 12, calories = 412
            ),
            R.drawable.breakfastburrito2 to RecipeData(
                "Breakfast Burrito",
                listOf("1/2 cup Milk Greek Plain Yogurt", "1/4 cup salsa", "1 tbsp fresh cilantro, chopped", "1/4 tsp garlic powder", "1/4 tsp cumin", "1/8 tsp table salt", "dash hot sauce, optional", "2 whole wheat flour tortillas", "4 large eggs", "1/4 tsp table salt", "1/8 tsp ground black pepper","1 Tbsp olive oil"
                ),
                "1. Stir together yogurt, salsa, cilantro, garlic powder, cumin, salt and hot sauce, if using, in a small bowl. Set aside.\n\n2. In another bowl, crack eggs and add salt and pepper. Whisk until frothy and pale yellow in color, about 30 seconds.\n\n3. In a nonstick pan, heat olive oil over medium-low. Add eggs, moving around the pan with a spatula as the edges start to set. Meanwhile, heat tortillas in a dry pan or directly over the flame of your burner. After about 1 minute, transfer eggs from pan to warmed tortillas.\n\n4. Divide salsa mixture between tortillas and add toppings if you like, such as beans, cheese, avocado or shredded chicken. Turn in sides and roll up your breakfast burrito with creamy salsa.\n",
                listOf(R.drawable.full_burrito2),
                proteins = 20, carbs = 18, fat = 12, calories = 248
            ),
            R.drawable.greensmoothie3 to RecipeData(
                "Green Smoothie",
                listOf("1 cup orange juice", "1/2 cup yogurt, plain", "4 cups fresh baby spinach", "1 cup frozen pineapple chunks", "1 green apple", "2 bananas"),
                "1. Add orange juice and yogurt to a blender jar. Top with fresh baby spinach and blend until smooth.\n\n2. Add the pineapple, apple, and bananas and blend until smooth. Add more juice (or water) to thin out the smoothie to your preference.\n\n3. Serve immediately or store in an airtight container for up to a day to ensure maximum freshness, nutrition, and flavor.\n",
                listOf(R.drawable.full_smoothie3),
                proteins = 2, carbs = 17, fat = 1, calories = 75
            ),
            R.drawable.vietnamchicken4 to RecipeData(
                "Vietnamese Chicken",
                listOf("1 double chicken fillet", "3 cloves of garlic", "2 medium onions", "1 spoon hot pepper", "1 spoon sweet pepper", "1 spoon curry", "30 ml soy sauce", "3 spoons sugar", "some olive oil", "1 pinch of salt and pepper"),
                "1. Cut the chicken into thick cubes, mix with spices and chopped garlic.\n\n2. Melt the sugar in a pan with a little olive oil to create caramel.\n\n3. Add the chicken and simmer until the entire chicken is covered with caramel. We can add some water.\n\n4. Add soy sauce, reduce the heat and simmer covered until the chicken slightly changes color to a darker color.\n\n5. Add the chopped onions.\n\n6. Serve with rice, salad or fries.\n",
                listOf(R.drawable.full_kurczak4),
                proteins = 12, carbs = 21, fat = 6, calories = 186
            ),
            R.drawable.pasta5 to RecipeData(
                "Pasta Carbonara",
                listOf("10 oz. dry Barilla Penne Pasta", "1 pinch of salt", "1/4 cup olive oil", "1/2 medium red onion", "1 large carrot", "2 cups broccoli florets", "1 medium red bell pepper", "1 medium yellow squash", "1 medium zucchini", "3 cloves garlic cloves", "1 cup tomatoes", "2 tsp dried Italian seasoning", "1/2 cup pasta water", "2 Tbsp fresh lemon juice", "1/2 cup shredded parmesan", "2 Tbsp chopped fresh parsley"),
                "1. Bring a large pot of water to a boil. Cook penne pasta in salted water according to package directions, reserve 1/2 cup pasta water before draining.\n\n2. Meanwhile heat olive oil in a 12-inch skillet over medium-high heat.\n\n3. Add red onion, carrot, broccoli and bell pepper and saute 2 minutes.\n\n4. Add squash and zucchini then saute 2 - 3 minutes or until veggies have nearly softened.\n\n5. Add garlic, tomatoes, and Italian seasoning and saute 2 minutes longer.\n\n6. Pour veggies into now empty pasta pot or a serving bowl, add drained pasta, drizzle in lemon juice, season with a little more salt as needed and toss while adding in pasta water to loosen as desired.\n\n7. Toss in 1/4 cup parmesan and parsley then serve with remaining parmesan on top.\n",
                listOf(R.drawable.full_pasta5),
                proteins = 4, carbs = 23, fat = 3, calories = 148
            ),
            R.drawable.pancakes6 to RecipeData(
                "Pancakes",
                listOf( "2 eggs,", "1 and 1/2 buttermilk,", "2 cups of wheat flour,", "50 g melted butter,", "4 tablespoons of powdered sugar,", "1 pinch of salt,", "2 spoons of baking powder,", "Serving additions: fruit, sprinkles, maple syrup."),
                "1. Combine the dry ingredients in a bowl - sift flour, salt, baking powder and sugar. Mix.\n\n2. Beat the eggs, combine them with melted butter and milk.\n\n3. Combine the dry ingredients with the egg mixture.\n\n4. Mix or blend until combined.\n\n5. Fry in a dry frying pan until golden on both sides.\n\n6. Serve pancakes with your favorite additions: peanut butter, fresh fruit, maple syrup or whipped cream.\n",
                listOf(R.drawable.full_pancakes6),
                proteins = 6, carbs = 26, fat = 9, calories = 210
            ),
            R.drawable.airfryerpotato7 to RecipeData(
                "Air Fryer Potato",
                listOf("2 sweet potatoes", "1 tbsp olive oil, plus 2 tsp", "1 small garlic bulb", "150g Greek yogurt", "3 tbsp tahini", "1 lemon, zested and juiced", "1 tbsp maple syrup", "5g fresh curry leaves", "small handful of coriander, finely chopped", "1 pinch of chilli flakes"),
                "1. Heat air-fryer to 200C. Pierce a few holes in the sweet potatoes using a sharp knife, then rub with 1 tbsp of the olive oil and sprinkle with salt and freshly ground black pepper.\n\n2. Drizzle 1 tsp of the oil over the garlic bulb, sprinkle with salt and pepper, then wrap in foil. Put both the garlic and sweet potatoes in the air-fryer and cook for 35 mins.\n\n3. Remove the garlic and set aside to cool slightly, then put the sweet potatoes back in for 15 mins until a cutlery knife goes through the centre with no resistance.\n\n4. Meanwhile, put the Greek yogurt in a small bowl and mix with the tahini, lemon zest and juice, maple syrup and some salt and pepper. When the garlic has cooled, squeeze the cloves into the tahini and yogurt mixture and stir well.\n\n5. Put the curry leaves, 1 tsp olive oil and a pinch of salt in a small bowl. Massage with your hands so they’re well coated. Put the curry leaves in the air fryer and place a small, ovenproof plate on top. Cook for 5-6 mins at 200C until crispy.\n\n6. Split the sweet potatoes open and fluff up the flesh slightly with a fork. Spoon the yogurt mixture over the sweet potatoes, then top with the crisp curry leaves, chopped coriander and chilli flakes.\n",
                listOf(R.drawable.full_airfryer7),
                proteins = 2, carbs = 10, fat = 5, calories = 87
            ),
            R.drawable.steak8 to RecipeData(
                "Steak with Vegetables",
                listOf("430g Maris Piper potatoes", "1 tbsp Cajun seasoning", "2 tbsp olive oil", "400g bavette steak", "1/4 small bunch of coriander", "For the spicy slaw", "1/4 small red cabbage", "1/2 red onion", "2 tbsp mayonnaise", "1 tbsp soured cream", "1/2 tbsp chipotle chilli paste"),
                "1. Heat the oven to 220C/200C fan/gas 7. Bring a large pan of salted water to the boil, add the potatoes and cook for 5 mins. Drain and leave to steam-dry in the colander for a few minutes.\n\n2. Toss the potatoes with the Cajun seasoning, half the oil and a good pinch of salt. Tip onto a baking tray and roast for 25-30 mins or until golden and crisp, tossing halfway through.\n\n3. To make the slaw, combine the cabbage, onion, mayonnaise, soured cream and chipotle paste in a bowl until the cabbage and onion are well coated. Set aside.\n\n4. Rub the steak with the remaining oil and season both sides. Heat a griddle pan or non-stick frying pan over a high heat until very hot.\n\n5. Add the steak to the pan, cook for 2 mins on one side, then 3 mins on the other. Remove, put on a chopping board, cover with foil and leave to rest for 5 mins. Slice into strips.\n\n6. Serve the steak with the potatoes scattered with the coriander and a pile of the spicy slaw.\n",
                listOf(R.drawable.full_steakcajun8),
                proteins = 8, carbs = 6, fat = 8, calories = 122
            ),
            R.drawable.fishpie9 to RecipeData(
                "Fish Pie",
                listOf("200g Maris Piper potatoes", "1 tbsp butter", "1 tbsp flour", "200ml whole milk", "1/2 tsp Dijon mustard", "100g cheddar cheese", "200g fish pie mix", "50g frozen peas", "50g shredded kale", "1 spring onion", "1 tbsp parmesan"),
                "1. Tip the potatoes into a pan, cover with hot water from the kettle and add a pinch of salt. Simmer for 15-20 mins until tender, then drain and leave to steam-dry.\n\n2. Meanwhile, warm the butter in a heavy-based pan over a low heat. Add the flour and stir until you have a paste. Gradually whisk in 200ml milk until you have a thick white sauce. Simmer for 3-4 mins, stirring.\n\n3. Season, then stir in the mustard and half the cheese, and continue to stir until the cheese has melted. Fold in the fish, peas and kale, and cook for 8 mins more, or until the kale wilts, the fish is just cooked and the prawns are starting to turn pink.\n\n4. Heat the grill to medium-high. Mash the potatoes with the spring onion and 1 tbsp milk. Fold in the remaining cheese and season well.\n\n5. Tip the filling into a heatproof dish and spoon the cheesy mash on top, swirling it with a fork until the filling is completely covered. Sprinkle over the parmesan and grill for 10-15 mins until the pie is golden and bubbling.\n",
                listOf(R.drawable.full_fishpie9),
                proteins = 6, carbs = 4, fat = 5, calories = 76
            )
        )
    )


    private val _selectedRecipe = mutableStateOf<RecipeData?>(null)
    val selectedRecipe: State<RecipeData?> = _selectedRecipe

    fun loadRecipe(imageId: Int) {
        _selectedRecipe.value = _recipes.value[imageId]
    }

    fun addToFavorites(recipe: RecipeData) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val foodCollection = db.collection("users").document(userId).collection("food")

            val foodData = hashMapOf(
                "name" to recipe.name,
                "calories" to recipe.calories,
                "proteins" to recipe.proteins,
                "carbs" to recipe.carbs,
                "fat" to recipe.fat
            )

            foodCollection.document(recipe.name).set(foodData)
                .addOnSuccessListener {
                    Log.d("Firestore", "Dodano do ulubionych!")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Błąd dodawania do ulubionych", e)
                }
        }
    }
}
