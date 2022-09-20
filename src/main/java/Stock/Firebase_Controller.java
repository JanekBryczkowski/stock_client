package Stock;

import com.google.auth.oauth2.GoogleCredentials; //import necessary for Firebase
import com.google.firebase.FirebaseApp; //import necessary for Firebase
import com.google.firebase.FirebaseOptions; //import necessary for Firebase
import com.google.firebase.database.*; //import necessary for Firebase

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList; //import needed for list to function
import java.util.List; //import needed for list to function

public class Firebase_Controller {

    //declaring json's location
    private static final String FILE_NAME = "./stock-application-81280-firebase-adminsdk-7wm1o-a52e51177b.json";

    //declaring Firebase's website
    private static final String DATABASE_NAME = "https://stock-application-81280.firebaseio.com/";

    //declaring variables
    private static FirebaseDatabase db;
    public static List<Person> persons = new ArrayList<>();

    public static List<Company> companies = new ArrayList<>();

    public static boolean adminStatus;

    //method for initializing the Firebase
    public static void initDatabase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(FILE_NAME);

        FirebaseOptions options = new FirebaseOptions.Builder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount)).
                setDatabaseUrl(DATABASE_NAME).build();

        FirebaseApp.initializeApp(options);

        db = FirebaseDatabase.getInstance();
    }

    //method for saving list of persons from Firebase into the persons list
    public static void getListOfPersons() {

        //getting instance of the Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        //getting reference of the "Object" wanted to be accessed in the Firebase
        DatabaseReference reference = database.getReference("User");
        persons = new ArrayList<>();

        //adding each person to the list
        reference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Person person = dataSnapshot.getValue(Person.class);
                persons.add(person);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("error");
            }
        });
    }

    //method for saving Pending order
    public static void savePendingOrder(List<PendingOrder> pendingOrders) {
        //getting reference of the "Object" wanted to be accessed in the Firebase
        DatabaseReference reference = db.getReference("User");

        String number;
        int indexOfUser = Main.IndexOfUser + 1;

        //algorithm for creating correct number
        if (indexOfUser < 10) {
            number = "0" + indexOfUser;
        } else {
            number = String.valueOf(indexOfUser);
        }

        //saving pending order list into the specific user in the Firebase
        reference.child("User" + number).child("pendingOrder").setValueAsync(pendingOrders);
    }

    //method for saving list of companies from Firebase into the companies list
    public static void getListOfCompanies() {

        //getting instance of the Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        //getting reference of the "Object" wanted to be accessed in the Firebase
        DatabaseReference reference = database.getReference("Company");

        companies = new ArrayList<>();

        //adding each company to the list
        reference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Company company = dataSnapshot.getValue(Company.class);
                companies.add(company);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("error");
            }
        });
    }

    //method for getting the current status of admin from the Firebase
    public static void getAdminStatus() {
        //getting instance of the Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        //getting reference of the "Object" wanted to be accessed in the Firebase
        DatabaseReference reference = database.getReference("Admin");

        //changing the status of admin boolean
        reference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Admin admin = dataSnapshot.getValue(Admin.class);
                if (admin.isStatus()) {
                    adminStatus = true;
                } else {
                    adminStatus = false;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("error");
            }
        });
    }

    //method for changing pending orders
    public static void changePendingOrder(List<PendingOrder> pendingOrders) {
        //getting reference of the "Object" wanted to be accessed in the Firebase
        DatabaseReference reference = db.getReference("User");

        //algorithm for getting correct number of user
        String index;
        int temp = Main.IndexOfUser + 1;
        String indexTemp = String.valueOf(temp);
        if (temp < 10) {
            index = "User0" + indexTemp;
        } else {
            index = "User" + indexTemp;
        }

        //saving pending order list to the Firebase
        reference.child(index).child("pendingOrder").setValueAsync(pendingOrders);
    }
}
