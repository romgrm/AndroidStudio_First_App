package fr.romgrm.MyFirstApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.romgrm.MyFirstApp.adapters.FoodItemAdapter;
import fr.romgrm.MyFirstApp.models.FoodItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /************ Liste des items ****************/

        // je créer un arrayList pour ma class FoodItem
        List<FoodItem> foodItemsList = new ArrayList<>();

        /* j'ajoute à mon arrayList les instances de foodItems => explications :

        J'aurai très bien pu faire une nouvelle instance de ma class FoodItem:

        FoodItem instanceFood = new FoodItem(name : "hamburger", price: 5);

        Puis l'ajouter à ma list :

        foodItemsList.add(instanceFood);

        Mais cette tehnique est plus rapide, créer l'instance et l'ajouter directement dans ma list
        */
        foodItemsList.add(new FoodItem("Beer", "beer", 9));
        foodItemsList.add(new FoodItem("Hamburger", "hamburger", 4));
        foodItemsList.add(new FoodItem("Hot-Dog", "hotdog", 8));
        foodItemsList.add(new FoodItem("Pizza", "pizza", 5));

        // je récupère ma Listview dans mon fichier activity_main.xml via son id
        ListView shopListView = findViewById(R.id.shop_list_view);

        /* le setAdapter permet d'envoyer des données à notre shopListView (qui contient lui même notre
        ListView qui affiche les éléments). Donc grâce à setAdapter, on envoie les données de foodItemsList
        (burger, hotdog...), ainsi que les infos de notre context actuel (this), via une nouvelle instance
        de notre class FoodItemAdapter (class ou l'on retrouve la method Inflater qui permet de convertir une
        vue .xml en objet view, autrement dit notre squelette d'item (img+textes) en un objet réutilisable.

        Donc on a remplie notre ListView de 3 choses ; le squelette d'affichage de nos items (via l'inflate)
        et la list d'items de nourriture, ainsi que le context de l'app*/
        shopListView.setAdapter(new FoodItemAdapter(this, foodItemsList));
    }
}