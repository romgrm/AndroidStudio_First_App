package fr.romgrm.MyFirstApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;

import java.util.List;
import java.util.zip.Inflater;

import fr.romgrm.MyFirstApp.R;
import fr.romgrm.MyFirstApp.models.FoodItem; // on import la list foodItemsList

/* Tips : alt+Enter sur le nom de la class -> Implements Method -> ok. ça permet d'importer les méthodes
abstraites de la class parent BaseAdapter et donc de ne plus avoir l'error */

public class FoodItemAdapter extends BaseAdapter {

    // on créer un attribut context qui va contenir toutes les infos/activité de l'application
    private Context context;
    private List<FoodItem> foodItemsList;
    private LayoutInflater inflater;

    // constructor qui va prendre en compte le context + notre list d'items

    public FoodItemAdapter(Context context, List<FoodItem> foodItemsList){
        this.context = context;
        this.foodItemsList = foodItemsList;
        this.inflater = LayoutInflater.from(context); // on donne les infos de notre app
    }


    // Implémentations des methods abstraites de BaseAdapter
    @Override
    /* la method getCount nous demande combien d'éléments il y'aura sur notre écran, donc on lui
    donne la taille de notre List d'items
    */
    public int getCount() {
        return foodItemsList.size();
    }

    @Override
    /* permet de retourner l'objet que l'on souhaite en fonction de sa position (en paramètre).
    Ici donc notre objet c'est nos instance créées, FoodItem, dans la Main de java, et on return
    la position actuelle de notre foodItemList, donc autrement dit la position de nos instances
    (hamburger, hot-dog...)*/
    public FoodItem getItem(int position) {
        return foodItemsList.get(position);
    }

    @Override
    /* ça nous return l'id de notre item, à ne pas toucher ! */
    public long getItemId(int i) {
        return 0;
    }

    @Override
    /* Element le plus important, c'est grâce à cette method qu'on va pouvoir personaliser chacun
    de nos éléments grâce au Inflater. En gros, notre fichier adapter_item.xml contient la structure
    de nos items (img+textes). Si on veut 5 items, il va falloir que se fichier "s'envoie" 5 fois à notre ListView
    dans notre fichier activity_main.xml, qui gère l'affichage de notre app. */
    /* le LayoutInflater permet de créer un nouvel objet 'view' ou 'layout' à partir d'un fichier .xml
    L'intérêt est d'avoir un objet réutilisable dans notre code
    On créer donc un inflate (gonflement = création) de notre layout adapter_item*/

    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.adapter_item, null);

        /* maintenant qu'on a réussi à créer le nombre d'items suffisant via le inflater, il faut
        remplir chq items en fonctions des données rentrées (name et price */

        /* je recupere l'item courant de FoodItem via la fonction getItem (ecrite plus haut) qui elle
        meme recupere la list d'items (donc les infos à avoir). (i=0 alors ça sera l'hamburger)
        Donc je vais pouvoir récupérer chq nom + prix de mon FoodItem, vu que ce FoodItem est remplie
        par ma FoodItemList qui elle même est remplie par hamburger, hot-dog etc*/

        FoodItem currentItem = getItem(i);

        // je récupère le nom , le prix et l'icon de mon item courant
        String itemName = currentItem.getName();
        double itemPrice = currentItem.getPrice();
        String itemMnemonic = currentItem.getMnemonic();

        /* je récupère mon squelette de Titre via son id : 'item_name' de mon adapter_item.xml afin
        de changer sa valeur par défaut, par le name de l'item courant */
        TextView itemNameView = view.findViewById(R.id.item_name);
        itemNameView.setText(itemName);

        // pareil pour le prix
        TextView itemPriceView = view.findViewById(R.id.item_price);
        itemPriceView.setText(itemPrice + "€");

        /* Pour les icons on récupère aussi notre squelette d'icon via son id. Ensuite on utilise la
        fonction 'setImageRessource' qui a besoin d'une variable "ressource id". Cette variable on l'a
        remplie avec le context + ce que contient notre variable ressourceName + le dossier ou sont nos
        icons + le package.
        Et pour sélectionner bien nos icons et pour qu'a chq fois ce soit le bon icon avec le bon titre/prix,
        on utilise un mnemonic (hamburger, beer...) qu'on a inclue dans le nom du fichier, entre "item_" et
        "_icon". Donc a chq fois que itemMnemonic changera, genre "hamburger", le ressourceName deviendra
        "item_hamburger_icon" et donc notre setImageResource viendra chercher dans le dossier drawable l'icon
        du même nom */
        ImageView itemIconView = view.findViewById(R.id.item_icon);
        String ressourceName = "item_" + itemMnemonic + "_icon";
        int resId = context.getResources().getIdentifier(ressourceName, "drawable", context.getPackageName());
        itemIconView.setImageResource(resId);


        /* Afficher un message au clic */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Do you want this super " + itemName + " to miam at the prix of " + itemPrice + "€ ?", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
