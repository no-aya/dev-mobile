# TP2 : Splash screen-Navigation entre Écrans- Internationalisation
## Démonstration

https://user-images.githubusercontent.com/106016869/221311431-03b6d260-b41a-44f4-9ec5-759cade4bc46.mp4

## Interfaces
![Presentation1](https://user-images.githubusercontent.com/106016869/221314412-9b6afb0e-3a8f-44ed-868f-ea03ee4e3950.jpg)

## Partie 1 : Interfaces Graphiques
Cette application compte 3 interfaces :
- Splash Screen : Contient le logo de l'application et son nom
- Main Screen : Contient 2 champs de saisie [Email, Mot de passe] et un bouton [Se connecter, Réniitialiser]
- Connexion Screen : Affiche le [Email, Mot de passe] et un bouton [Retour]

### activity_splash.xml
Cette interface contient le logo de l'application et son nom
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent" android:gravity="center">

    <ImageView
        android:id="@+id/logo_id"
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:layout_centerInParent="true"
        android:src="@drawable/baseline_article_24"
        android:contentDescription="@string/description"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/logo_id"
        android:layout_centerHorizontal="true"
        android:text="@string/app_name"
        android:textSize="30sp"/>

</RelativeLayout>
```
Note : Le logo est un fichier vectoriel au format .xml
Note : Toutes les valeurs textuelles sont stockées dans le fichier strings.xml, nous en parlerons ultérieurement

### activity_main.xml
Cette interface contient 2 champs de saisie [Email, Mot de passe] et un bouton [Se connecter, Réniitialiser]

Les champs de saisie sont des composants Material Design
```xml
<com.google.android.material.textfield.TextInputLayout 
    android:layout_width="match_parent" 
    android:layout_height="wrap_content" 
    android:layout_marginTop="15dp" 
    android:hint="@string/deuxieme_champs">
    <com.google.android.material.textfield.TextInputEditText 
        android:id="@+id/deuxiemechamp" 
        android:layout_width="match_parent" 
        android:layout_height="wrap_content" 
        android:inputType="textPassword"/>
</com.google.android.material.textfield.TextInputLayout>
```
Note : Nous utilisons Material Design version 2 

### activity_result.xml
C'est l'interface d'accueil, elle affichera les coordonnées saisis par l'utilisateur dans les champs de saisie de l'interface Main Screen.

## Partie 2 : Splash Screen
Le Splash Screen est l'écran d'accueil de l'application, il est affiché au lancement de l'application et il disparait au bout de 3 secondes.

Pour configurer le Splash Screen, nous devons modifier le fichier AndroidManifest.xml
```xml
 <activity 
    android:name=".SplashActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
<activity android:name=".MainActivity" />
```
Comme cela, SplashActivity sera l'activité principale de l'application.

Puis nous devons créer une classe SplashActivity.java
```java
public class SplashActivity extends Activity{
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Toast
        Toast.makeText(this,getString(R.string.toast_message),Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        },3000);
    }
}
```
Cette classe hérite de la classe Activity, elle contient un Handler qui permet de déclencher une action après un certain temps. Dans notre cas, nous déclencherons l'action après 3 secondes grâce à `Handler.postDelayed()`. Une fois le temps écoulé, nous allons lancer l'activité principale de l'application.

Intent signifie intention, c'est une classe qui permet de définir une action dans le future, dans notre cas, nous voulons lancer l'activité principale.


## Partie 3 : Navigation entre Écrans
Pour naviguer entre les écrans, nous devons utiliser la classe Intent, nous avons déjà utilisé cette classe dans la partie précédente.

### MainActivity.java
```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,getString(R.string.tutorial),Toast.LENGTH_LONG).show();
        //Send result to ResultActivity

        premierchamp=findViewById(R.id.premierchamp);
        deuxiemechamp=findViewById(R.id.deuxiemechamp);


        assignId(btnconn,R.id.connexion);
        assignId(btnRen,R.id.cancel_button);

    }

    void assignId (MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();

        if(buttonText.equals(getString(R.string.reset))){
            premierchamp.setText("");
            deuxiemechamp.setText("");
            return;
        }
        if(buttonText.equals(getString(R.string.conn))){
            //send result to ResultActivity
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("username",premierchamp.getText().toString());
            intent.putExtra("password",deuxiemechamp.getText().toString());
            startActivity(intent);
        }

    }
```
Dans cette classe, nous avons défini 2 champs de saisie et 2 boutons. Nous avons aussi défini une fonction `assignId()` qui permet d'assigner un id à un bouton et d'ajouter un écouteur d'événement sur ce bouton.

Lorsque l'utilisateur clique sur le bouton [Se connecter], nous allons lancer l'activité `ResultActivity` et envoyer les données saisies par l'utilisateur. Ses données seront stockées dans un objet Intent. Nous allons utiliser la méthode `putExtra()` pour stocker les données dans l'objet Intent. Cette méthode prend 2 paramètres, le premier est le nom de la donnée et le deuxième est la valeur de la donnée. Nous allons utiliser ces données dans l'activité `ResultActivity`.

### ResultActivity.java
```java
public class ResultActivity extends AppCompatActivity {

        TextView user,pass;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
            user=findViewById(R.id.userResult);
            pass=findViewById(R.id.passresult);
            Intent intent=getIntent();
            String resultText=intent.getStringExtra("username");
            String resultText2=intent.getStringExtra("password");
            user.setText(resultText);
            pass.setText(resultText2);
        }

    public void returnMain(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
```
Dans cette classe, nous avons défini 2 TextView, nous allons afficher les données saisies par l'utilisateur dans ces TextView. Nous allons utiliser la méthode `getIntent()` pour récupérer l'objet Intent qui contient les données saisies par l'utilisateur. Nous allons utiliser la méthode `getStringExtra()` pour récupérer les données. Cette méthode prend en paramètre le nom de la donnée. Nous allons afficher ces données dans les TextView. 

Une fois l'utilisateur clique sur le bouton [Retour], nous allons relancer l'activité `MainActivity`.

## Partie 4 : Internationalisation
L'internationalisation est une technique qui permet de traduire une application dans plusieurs langues. Pour internationaliser une application, nous devons créer un fichier de ressources pour chaque langue. Dans notre cas, nous allons créer un fichier de ressources pour le français et un autre pour l'arabe, alors qu'on garde l'anglais comme langue par défaut.

### strings.xml (default)
```xml
<resources>
    <string name="app_name">[TP2] Login</string>
    <string name="premier_champs">Email</string>
    <string name="deuxieme_champs">Password</string>
    <string name="conn">Connexion</string>
    <string name="reset">Reset</string>
    <string name="back">Return</string>
    <string name="description" translatable="false">Icon for the app</string>
    <string name="toast_message" translatable="false">Presented by Aya</string>
    <string name="tutorial">Write your information</string>
    <string name="toast_missu">Your work is still on hold !</string>
    <string name="toast_res">Restarting your working!</string>
    <string name="toast_back">Welcome back! Resume your work…</string>
    <string name="titre">Your information</string>
</resources>
```
Afin de traduire aux 2 autres langues, nous devons créer 2 fichiers de ressources, un pour le français et un pour l'arabe. Nous allons créer un fichier de ressources pour le français et un autre pour l'arabe, et nous allons utiliser l'éditeur d'Android Studio pour faciliter la traduction.

//TODO: Ajouter screeshot du tableau.

Maintenant notre application peut s'adapter à plusieurs langues, comme nous l'avons testé en haut.

## Partie 5 : Toast
Toast est une classe qui permet d'afficher un message à l'écran. Nous allons utiliser cette classe pour afficher un message à l'écran lorsque :
- L'utilisateur ouvre l'application (mode Create)
- L'utiliateur sort de l'application (mode Pause)
- L'utilisateur revient dans l'application (mode Resume)
- Message d'accueil au début

### MainActivity.java
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    {...}
    Toast.makeText(this,getString(R.string.tutorial),Toast.LENGTH_LONG).show();
    {...}
}
@Override
protected void onPause() {
   Toast.makeText(this,getString(R.string.toast_missu),Toast.LENGTH_LONG).show();
   super.onPause();
}

@Override
protected void onRestart() {
    Toast.makeText(this,getString(R.string.toast_res),Toast.LENGTH_LONG).show();
    super.onRestart();
}

@Override
protected void onResume() {
    Toast.makeText(this,getString(R.string.toast_back),Toast.LENGTH_LONG).show();
    super.onResume();
}
```
Dans cette classe, nous avons ajouté 4 méthodes pour afficher les messages à l'écran. Nous avons utilisé la méthode `makeText()` pour créer un objet Toast. Cette méthode prend 3 paramètres, le premier est le contexte de l'application, le deuxième est le message à afficher et le troisième est la durée d'affichage du message. Nous avons utilisé la méthode `show()` pour afficher le message à l'écran.

