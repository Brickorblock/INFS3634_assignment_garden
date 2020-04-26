package com.example.infs3634_assignment_garden;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Helper;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Question;
import com.example.infs3634_assignment_garden.entities.Quiz;
import com.example.infs3634_assignment_garden.entities.Topics;
import com.example.infs3634_assignment_garden.entities.subclasses.Evergreen;
import com.example.infs3634_assignment_garden.entities.subclasses.LemonTree;
import com.example.infs3634_assignment_garden.entities.subclasses.OrangeTree;
import com.example.infs3634_assignment_garden.ui.QuestionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public static Garden garden;
    public static NavController navController;
    public static AppDatabase appDatabase;

    public Plant plant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_garden, R.id.navigation_quiz,
                R.id.navigation_learn, R.id.navigation_help,
                R.id.navigation_shop, R.id.questionFragment,
                R.id.resultFragment, R.id.chapterFragment,
                R.id.videosFragment, R.id.youtubeFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Db")
                .build();

        //create Garden class (stores global info about progress, etc.)

        this.garden = new Garden();

        // instantiate DB tables and populate data
        new PopulateQuestionsAsyncTask().execute();
        new ShowData().execute();
        new PopulateGardenTask().execute();
        new PopulatePlantAsyncTask().execute();

    }

    @Override
    public void onBackPressed() {
        navController.popBackStack();

    }

    private static class PopulateQuestionsAsyncTask extends AsyncTask<Void, Void, Question[]> {

        @Override
        protected Question[] doInBackground(Void... voids) {
            List<Question> Questions = new ArrayList<>();
            Question[] questionArray = null;

            if (appDatabase.questionsDao().getData().isEmpty()) {
                //VERY IMPORTANT LINE...
                appDatabase.questionsDao().deleteAllQuestions();
                Log.d("Main Activity", "adding starter data");
                //...VERY IMPORTANT LINE
                Questions.add(new Question("Solar Systems", "Which of the following is an example of a celestial body?", "Sun", "Moon", "Stars", "All of the Above", "All of the Above"));
                Questions.add(new Question("Solar Systems", "Which planet has rings around it?", "Jupiter", "Saturn", "Uranus", "All of the Above", "All of the Above"));
                Questions.add(new Question("Solar Systems", "The Sun is ___ million km away from the Earth.", "100", "150", "200", "250", "150"));
                Questions.add(new Question("Solar Systems", "The diameter of Moon is ______ that of the earth.", "1/2", "1/3", "1/4", "2/3", "1/4"));
                Questions.add(new Question("Solar Systems", "The moon moves around the earth in about _____ days.", "11", "33", "60", "27", "27"));
                Questions.add(new Question("Solar Systems", "The moon takes _____ days to complete one spin.", "27", "35", "16", "9", "27"));
                Questions.add(new Question("Solar Systems", "The majority of asteroids are found between the orbits of:", "Mars and Jupiter", "Earth and Mars", "Jupiter and Saturn", "Saturn and Uranus", "Mars and Jupiter"));
                Questions.add(new Question("Solar Systems", "Which planet takes longest time for one spin on its axis?", "Venus", "Mercury", "Saturn", "Uranus", "Mercury"));
                Questions.add(new Question("Solar Systems", "Which planet takes longest time to orbit around the Sun?", "Neptune", "Mercury", "Saturn", "Uranus", "Neptune"));
                Questions.add(new Question("Solar Systems", "Which is the most abundant in Venus’ atmosphere?", "Nitrogen", "Carbon Dioxide", "Oxygen", "Ozone", "Carbon Dioxide"));
                Questions.add(new Question("Solar Systems", "Which of the following is true of our solar system?", "Not all planets orbit the sun in the same direction", "Not all planets spin in the same direction as they orbit", "It has an equal number of stars and planet", "It is less than 1 million years old", "Not all planets spin in the same direction as they orbit"));
                Questions.add(new Question("Solar Systems", "Venus is most similar in mass and density to which solar system object?", "Jupiter", "Earth", "Mars", "Phobos", "Earth"));
                Questions.add(new Question("Solar Systems", "Which of the following is NOT a dwarf planet?", "Pluto", "Charon", "Eris", "Ceres", "Charon"));
                Questions.add(new Question("Solar Systems", "When did the solar system form?", "3.2 billion years ago", "10.8 billion years ago", "7.5 billion years ago", "4.6 billion years ago", "4.6 billion years ago"));
                Questions.add(new Question("Solar Systems", "Which of the following is an asteroid?", "Eris", "Charon", "Vesta", "Pluto", "Vesta"));
                Questions.add(new Question("Solar Systems", "A greenhouse gas traps ____ in the atmosphere", "Ozone", "Gamma rays", "Heat", "Ultraviolet radiation", "Heat"));
                Questions.add(new Question("Solar Systems", "Which is the reason why Venus is hotter than Mercury?", "It’s closer to the sun", "It’s size", "The greenhouse effect", "Climate change", "The greenhouse effect"));
                Questions.add(new Question("Solar Systems", "Which moon on Saturn is considered to have the best chance at hosting life?", "Enceladus", "Mimas", "Rhea", "Phoebe", "Enceladus"));
                Questions.add(new Question("Solar Systems", "What is the main component of Venus’ clouds?", "Water vapour", "Sulphuric acid", "Methane", "Carbon dioxide", "Sulphuric acid"));
                Questions.add(new Question("Solar Systems", "Name the molecule or element that is part of the current atmosphere but would not be in the original atmosphere of the Earth:", "Carbon Dioxide", "Oxygen", "Methane", "Nitrogen", "Oxygen"));

                Questions.add(new Question("Cosmology", "The expansion age of the universe is about _________", "10.2 billion years", "13.7 billion years", "5.3 billion years", "16.9 billion years", "13.7 billion years"));
                Questions.add(new Question("Cosmology", "The rate of expansion of the universe is __________", "Decelerating", "Accelerating", "Constant", "Zero", "Accelerating"));
                Questions.add(new Question("Cosmology", "What type of galaxy shape is the milky way?", "Elliptical", "Irregular", "Spiral", "Barred spiral", "Spiral"));
                Questions.add(new Question("Cosmology", "What can be said about the location of the centre of our expanding universe?", "The Earth is at the center", "The Sun is at the center", "the Milky Way Galaxy is at the center", "The universe does not have a center", "The universe does not have a center"));
                Questions.add(new Question("Cosmology", "How are galaxies important to our existence?", "Without galaxies, the universe could not be expanding", "Without galaxies, there could not have been a Big Bang", "Galaxies recycle elements produced in stars into future star generations", "Galaxies provide the gravity that prevents us from falling off the earth", "Galaxies recycle elements produced in stars into future star generations"));
                Questions.add(new Question("Cosmology", "Galaxies that move closer to us show a what shift?", "Red Shift", "Green Shift", "Orange Shift", "Blue Shift", "Red Shift"));
                Questions.add(new Question("Cosmology", "In relation to us, most galaxies are…", "Moving away from us", "Moving toward us", "Not moving", "Moving perpendicular to us", "Moving away from us"));
                Questions.add(new Question("Cosmology", "Why is the CMB so cool now?", "The expansion of the universe has cooled the radiation", "Dense clouds of dust have blocked most of it", "The cosmological constant has cooled it", "Hydrogen atoms have condensed on it and chilled it", "The expansion of the universe has cooled the radiation"));
                Questions.add(new Question("Cosmology", "Which piece of evidence supports the Big Bang theory?", "The more distant galaxies are moving the slowest", "The more distant galaxies are moving the quickest", "The more distant galaxies are moving towards us", "Galaxies and space is not changing", "The more distant galaxies are moving the quickest"));
                Questions.add(new Question("Cosmology", "The \"afterglow\" of the energy from the origin of the universe that we see today", "Sunlight", "Cosmic explosions", "Cosmic background radiation", "Doppler effect", "Cosmic background radiation"));
                Questions.add(new Question("Cosmology", "Who discovered that the galaxies were moving away from one another?", "Issac Newton", "Albert Einstein", "Galileo Galile", "Edwin Hubble", "Edwin Hubble"));
                Questions.add(new Question("Cosmology", "What happens if you reverse the galaxy motion and go back in time?", "Everything must have been condensed into a singularity", "Everything would still be where it is today", "Some things would remain, some would have moved back", "Nobody has a clue", "Everything must have been condensed into a singularity"));
                Questions.add(new Question("Cosmology", "What happened to the radiation emitted from the Big Bang?", "It is visible in telescopes", "It has stretched out to the microwave part of the spectrum", "It has moved to the gamma ray part of the spectrum", "It is too old and no longer exists", "It has stretched out to the microwave part of the spectrum"));
                Questions.add(new Question("Cosmology", "What is the universe made up of?", "Energy", "Matter/dark matter", "Spacetime", "All of the above", "All of the above"));
                Questions.add(new Question("Cosmology", "Hubble's law states which of the following?", "We live in an expanding universe", "Galaxies far away look redder through a telescope", "The further away a galaxy is, the faster it is receding", "All of these", "All of these"));
                Questions.add(new Question("Cosmology", "The first particles of matter included which of the following?", "Electrons", "Helium atoms", "Carbon atoms", "All of these", "Electron"));
                Questions.add(new Question("Cosmology", "What does the strong nuclear force control?", "It attracts all matter to each other", "It holds protons and neutrons in nuclei together", "It holds electrons in orbit in atoms", "Radioactive decay", "It holds protons and neutrons in nuclei together"));
                Questions.add(new Question("Cosmology", "What percentage of the universe do scientists predict dark energy to occupy?", "90%", "38%", "68%", "52%", "68%"));
                Questions.add(new Question("Cosmology", "What is NOT true about the big bang?", "It started 13.7 billion years ago", "At one point space was moving faster than light itself", "The 4 fundamental forces were combined together at the start", "The speed of light was reduced at the beginning", "The speed of light was reduced at the beginning"));
                Questions.add(new Question("Cosmology", "What is a singularity?", "A zone of infinite pressure, and density that is infinitely small and hot", "No one knows", "A zone of specific pressure and density", "A zone of low pressure and density that is small and hot", "A zone of infinite pressure, and density that is infinitely small and hot"));

                Questions.add(new Question("Stars", "We believe there is a black hole at the centre of our galaxy because:", "The region appears dark in optical images", "We detect bursts of EM radiation from the centre.", "All stars near the centre orbit a massive central object.", "Predictive algorithms show it.", "All stars near the centre orbit a massive central object."));
                Questions.add(new Question("Stars", "How long does it take for Alpha Centauri’s light to reach Earth?", "About 50 months", "About 50 days", "About 50 minutes", "About 50 years", "About 50 months"));
                Questions.add(new Question("Stars", "Quasars are:", "A type of neutron stars", "A dying star", "A type of star", "Powered by supermassive blackholes", "Powered by supermassive blackholes"));
                Questions.add(new Question("Stars", "What is the lifecycle of an average star:", "Nebula, Main Sequence Star, Supernova, White Dwarf", "Nebula, Main Sequence Star, Planetary Nebula, White Dwarf", "Main Sequence Star, Nebula, Planetary Nebula, White Dwarf", "Nebula, Main Sequence Star, White Dwarf, Planetary Nebula", "Nebula, Main Sequence Star, Planetary Nebula, White Dwarf"));
                Questions.add(new Question("Stars", "What is the main reason a Star collapses into a black hole?", "Gravitational forces finally exceed the star’s nuclear forces", "It passes the age of 3 billion years", "The star’s core becomes full of helium", "It has lost too much mass", "Gravitational forces finally exceed the star’s nuclear forces"));
                Questions.add(new Question("Stars", "What are stars mostly comprised of?", "Helium", "Hydrogen", "Carbon", "Iron", "Hydrogen"));
                Questions.add(new Question("Stars", "Which is the closest star to us?", "Proxima Centauri", "Sirius", "Alpha Centauri", "Ross 128", "Alpha Centauri"));
                Questions.add(new Question("Stars", "Stars are glowing spheres of hot…", "Dust", "Metal", "Liquid", "Gas", "Gas"));
                Questions.add(new Question("Stars", "What is a light-year?", "How far light travels in a year", "How long it takes light to reach earth", "How long it takes light to reach the Sun", "A measurement of time", "How far light travels in a year"));
                Questions.add(new Question("Stars", "What process fuels a star?", "Nuclear fission", "Nuclear fusion", "Dark energy", "Neutrino creation", "Nuclear fusion"));
                Questions.add(new Question("Stars", "What type is out star?", "Red giant", "White Dwarf", "Yellow Main Sequence", "Blue giant", "Yellow Main Sequence"));
                Questions.add(new Question("Stars", "What colour are the hottest stars?", "Red", "Yellow", "White", "Blue", "Blue"));
                Questions.add(new Question("Stars", "Apparent magnitude is what?", "How bright a star is relative to us", "How bright a star really is", "The size of a star relative to us", "How large a star really is", "How bright a star is relative to us"));
                Questions.add(new Question("Stars", "What protects the Earth from Solar Wind?", "Greenhouse effect", "Earth’s magnetosphere", "The ozone layer", "Asteroid belt", "Earth’s magnetosphere"));
                Questions.add(new Question("Stars", "What is the product of two neutron stars colliding?", "Neutrinos", "Solar Wind", "Gravitational waves", "Nuclear fusion", "Gravitational waves"));
                Questions.add(new Question("Stars", "Where is the energy in the Sun generated?", "Just under the surface", "In the mantle", "In the radiative zone", "In its core", "In its core"));
                Questions.add(new Question("Stars", "What are the layers of the Sun (in asc order)?", "Core, Radiative, Convection, Photosphere, Chromosphere, Corona", "Core, Radiative, Corona, Convection", "Core, Convection, Corona, Photosphere, Chromosphere, Radiative", "Core, Corona, Chromosphere", "Core, Radiative, Convection, Photosphere, Chromosphere, Corona"));
                Questions.add(new Question("Stars", "What colour is the coolest star?", "Yellow", "White", "Blue", "Red", "White"));
                Questions.add(new Question("Stars", "Which property of a star tells us the composition of that Star?", "Luminosity", "Apparent magnitude", "Spectrum", "Absolute magnitude", "Spectrum"));
                Questions.add(new Question("Stars", "A star that explodes is known as a", "Nebula", "Supershell", "Neutron star", "Supernova", "Supernova"));

                questionArray = Questions.toArray(new Question[Questions.size()]);
                appDatabase.questionsDao().insert(questionArray);

                //testing...
                Question q1 = questionArray[0];
                Question q2 = questionArray[1];
                Question q3 = questionArray[2];
                Log.d("TAG", "doInBackground: 1 questionID in db = " + appDatabase.questionsDao().getData().get(q1.getId()).getId());
                Log.d("TAG", "doInBackground: 1 questionID in code = " + q1.getId());
                Log.d("TAG", "doInBackground: 2 questionID in db = " + appDatabase.questionsDao().getData().get(q2.getId()).getId());
                Log.d("TAG", "doInBackground: 2 questionID in code = " + q2.getId());
                Log.d("TAG", "doInBackground: 3 questionID in db = " + appDatabase.questionsDao().getData().get(q3.getId()).getId());
                Log.d("TAG", "doInBackground: 3 questionID in code = " + q3.getId());

            }

            return questionArray;
        }
    }


    public class ShowData extends AsyncTask<Void, Void, List<Question>> {

        @Override
        protected List<Question> doInBackground(Void... voids) {

            List<Question> allQuestions = appDatabase.questionsDao().getData();

            Log.d("Main Activity:", "all questions: " + appDatabase.questionsDao().getData());
            Log.d("Main Activity:", "all questions: " + appDatabase.questionsDao().getData().size());

            return allQuestions;
        }


    }

    private static class PopulatePlantAsyncTask extends AsyncTask<Void, Void, Plant[]> {

        @Override
        protected Plant[] doInBackground(Void... voids) {
            Plant[] plantArray = null;
            if (appDatabase.plantDao().getPlant().isEmpty()) {
                // populate some starter data
                appDatabase.plantDao().deleteAllPlant();

                //some dummy data for examining purposes

                garden.plants.add(new Evergreen(true));
                garden.plants.add(new LemonTree(true));
                garden.plants.add(new OrangeTree(true));
                // set plantIndexes for these new plants
                Helper.setPlantIndexes(garden.plants);

                Log.d("TAG", "doInBackground: 1 plantIndexSearch = " + garden.plantIndexSearch(garden.plants.get(0)));
                Log.d("TAG", "doInBackground: 2 plantIndexSearch = " + garden.plantIndexSearch(garden.plants.get(1)));
                Log.d("TAG", "doInBackground: 3 plantIndexSearch = " + garden.plantIndexSearch(garden.plants.get(2)));

                plantArray = garden.plants.toArray(new Plant[garden.plants.size()]);
                appDatabase.plantDao().insert(plantArray);

                Plant plant1 = plantArray[0];
                Log.d("TAG", "doInBackground: 1 plantId in db = " + appDatabase.plantDao().getPlantIndex(plant1.getPlantIndex()));
                Log.d("TAG", "doInBackground: 1 plantId in code = " + plant1.getPlantIndex());
            } else  {
                //fetch existing data from db
                garden.plants = (ArrayList<Plant>) appDatabase.plantDao().getPlant();
            }

            //since we aren't storing plantImages (array) in db, we have to populate this manually,
            // from the code, each time the app is launched
            Helper.populatePlantImages(garden.plants);

            return plantArray;
        }


    }

    private static class PopulateGardenTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {

            // instantiate some dummy data if db is empty (starting coin balance)
            if (appDatabase.gardenDao().getGarden().isEmpty()) {
                Log.d("TAG", "doInBackground: adding coins");
                appDatabase.gardenDao().delete();
                garden.setCoins(5000);
                appDatabase.gardenDao().insert(garden);

            } else {
                // fetch the amt in database
                int coinsInDb = appDatabase.gardenDao().getCoin(garden.getId());
                garden.setCoins(coinsInDb);

            }

            Log.d("TAG", "doInBackground: coins in db = " + appDatabase.gardenDao().getCoin(garden.getId()));
//            Log.d("TAG", "doInBackground: gardenID in db = " + appDatabase.gardenDao().getGarden().get(garden.getId()).getId());
            Log.d("TAG", "doInBackground: gardenID in code = " + garden.getId());

            return garden.getCoins();
        }
    }



}
