package com.example.spacedrifters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class ShopInfo extends Shop {
    private Context context;
    private String[] infoHundred = new String[3];
    private String[] infoTwoHundred = new String[10];
    private String infoFiveHundred;
    private String[] titleHundred = new String[3];
    private String[] titleTwoHundred = new String[10];
    private String titleFiveHundred;
    private final String inUseGame = "\n\nCheck it out in the game!";

    /**
     * The constructor of the class ShopInfo
     * @param context - global information about the application environment (given through level)
     */
    public ShopInfo(Context context) {
        this.context = context;
        infoHundred[0] = "The SC Serpent is one of the first spaceships of the SpaceCraft-Class that " +
                "was developed and build by the HEI (Human Empire India) in year 2119. One of the " +
                "most significant parts is the big deuterium photon canon that fires torpedoes with " +
                "exploding power of 64,5 MT. The maximum velocity is 8 * light-velocity which is " +
                "quite slow and reasoned in the early development of the 22th century.  The slogan " +
                "of the SC Serpent is 'shakti ke maadhyam se shaanti' which is Hindi for 'peace " +
                "through strength'." + inUseGame;
        infoHundred[1] = "Screlqul is the brother of Orgoiks. When he grew up, he was a bit of an " +
                "outcast. These days he is a party animal, but now and then he wrecks the party house, " +
                "because of his reckless behaviour."+ inUseGame;
        infoHundred[2] = "Orgoiks is the sister of Screlqul. She used to be the most popular alien " +
                "in the universe and had a good bond with her brother, even though he was an outcast. " +
                "Sometimes she goes to a party with him, but she always leaves before he wrecks it."+ inUseGame;
        infoTwoHundred[0] = "The NBWS Wilfred is a strong space fighter of the Noord-Brabant-War-" +
                "Ship-Class and was in the beginning a civil ship, but after the independence war " +
                "of the south provinces of the Netherlands in 2103, the winners Noord-Brabant, " +
                "Zeeland and Gelderland founded the NNR (Nieuw Nederlands Rijk) where the Dutch " +
                "Space Marine Corps (DSMC) rebuild all ships and got one of the most powerful space " +
                "forces on earth. With 19 * light-velocity and 57 MT is the NBWS Wilfred the most " +
                "powerful warship in the so called “Koninklijke ruimtevaart vloot”. The slogan is " +
                "'waakt, vertrouwende op God' which is Dutch for 'watch, trusting in God'."+ inUseGame;
        infoTwoHundred[1] = "The HMS Mary is a spaceship of the Her-Majesty’s-Ship-Class that fights " +
                "under the flag of the Royal Space Strike Forces of Great Britain (RSSFG). This ship, " +
                "named after the Queen of Great Britain in year 2154, is with 19 * light-velocity " +
                "faster than the SC Serpent but with 98,2 MT torpedoes the HMS Mary has a bigger " +
                "exploding power. The slogan of the HMS Mary is 'semper eadem'. Latin for 'always " +
                "the same'."+ inUseGame;
        infoTwoHundred[2] = "The SC Ghunne is the last built ship of the SpaceCraft-Class that was " +
                "developed by the AF (Asian Front) that is not existing anymore since 2215. After " +
                "a brutal civil war, the AF was biased by the AE (American Empire) and the JSF " +
                "(Japanese space Force). With 3 * light-velocity it is one of the slowest ships " +
                "ever build but with 193 MT the most powerful canon that makes till today the " +
                "American and Japanese engineers jealous."+ inUseGame;
        infoTwoHundred[3] = "The GWS Berlin, is the new development of the German-War-ship-Class " +
                "and is named after the capital of the 6th German Union and was built in 2134. The " +
                "17 * light-velocity fast space carrier was first build as troop transport spaceship " +
                "but after huge damages in the Venus-war, the troopship got the newest quantum photon " +
                "torpedoes which explode at two points at the same time because of the use of the " +
                "superposition-principle with both 36 MT. It brings fear in all universal empires."+ inUseGame;
        infoTwoHundred[4] = "Valqed is a shy little guy, but has a big heart. He befriended almost every " +
                "alien in the universe. When a friend is in need of a hug, he will always be there. The " +
                "thing is that his friends don't like hugs. That is the sad part of his life. Because of " +
                "that, he always sits in the corner, making up his own love stories."+ inUseGame;
        infoTwoHundred[5] = "Gurkrox is a hateful little creature. He does not like showering and you can " +
                "smell that really good. Aliens tend to stay away from him, but he does not mind. He rather " +
                "spends his time with trash anyways."+ inUseGame;
        infoTwoHundred[6] = "Churzo is the oldest sister of Kraecras, Qukail and Aaxans. She is a bit of a " +
                "tomboy and gets into trouble a lot of times. Her younger siblings still look up to her, but " +
                "her parents don't approve it. She spends her free time on the universal sport centre. She is " +
                "good at almost every sport, especially spaceship destroying."+ inUseGame;
        infoTwoHundred[7] = "Kraecras lives peacefully with his siblings, but things are often rough for the " +
                "middle alien. He is not good at sports like his older sister, instead he really likes baking " +
                "starcookies. Unfortunately sometimes the cookies get burned of the high speed they are baked " +
                "in. In the human world they call it meteorites."+ inUseGame;
        infoTwoHundred[8] = "Qukail is the twin brother of Aaxans and one of the youngest siblings. He likes to " +
                "watch the spaceships grand prix, but sometimes he gets so hyped that he gets involved into " +
                "spaceship trouble. Overall he is a really hyper alien, so we cannot really blame him."+ inUseGame;
        infoTwoHundred[9] = "Aaxans is the twin sister of Qukail. She likes watching series and eating pizza. " +
                "She is more of being inside and staying lazy. But do not touch her pizza, or you will get " +
                "your spaceship recked."+ inUseGame;
        infoFiveHundred = "The USS Thunder named after James Thunder, the 115th president of the AE " +
                "(American Empire), was finished in 2297 and is with 49 * light-velocity the fastest " +
                "ship of all even when the canon with 37 MT is the most powerless. Pilots in the " +
                "American Empire had to train at a special simulator at least five years to be allowed " +
                "to fly this speedy United-States-Ship-Class space fighter. The slogan of the USS " +
                "Thunder is “Courage, Commitment, Honor”. "+ inUseGame;

        titleHundred[0] = "Using SC Serpent!";
        titleHundred[1] = "Using Screlqul!";
        titleHundred[2] = "Using Orgoiks!";
        titleTwoHundred[0] = "Using NBWS Wilfred!";
        titleTwoHundred[1] = "Using HMS Mary!";
        titleTwoHundred[2] = "Using SC Ghunne!";
        titleTwoHundred[3] = "Using GWS Berlin!";
        titleTwoHundred[4] = "Using Valqed!";
        titleTwoHundred[5] = "Using Gurkrox!";
        titleTwoHundred[6] = "Using Churzo!";
        titleTwoHundred[7] = "Using Kraecras!";
        titleTwoHundred[8] = "Using Qukail!";
        titleTwoHundred[9] = "Using Aaxans!";
        titleFiveHundred = "Using USS Thunder!";
    }

    /**
     * A function which is responsible for setting the information of using an object that costs 100 coins
     * @param v - the view of the onClick function
     * @param i - an int which determines which bitmap gets chosen out of the array of bitmaps
     */
    public void usingHundred(View v, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(infoHundred[i]);
        builder.setTitle(titleHundred[i]);
        builder.setCancelable(false);
        builder.setNeutralButton("Great!", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * A function which is responsible for setting the information of using an object that costs 200 coins
     * @param v - the view of the onClick function
     * @param i - an int which determines which bitmap gets chosen out of the array of bitmaps
     */
    public void usingTwoHundred(View v, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(infoTwoHundred[i]);
        builder.setTitle(titleTwoHundred[i]);
        builder.setCancelable(false);
        builder.setNeutralButton("Great!", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * A function which is responsible for setting the information of using an object that costs 500 coins
     * @param v - the view of the onClick function
     */
    public void usingFiveHundred(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(infoFiveHundred);
        builder.setTitle(titleFiveHundred);
        builder.setCancelable(false);
        builder.setNeutralButton("Great!", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}