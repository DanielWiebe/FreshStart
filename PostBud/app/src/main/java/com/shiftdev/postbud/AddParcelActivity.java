package com.shiftdev.postbud;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shiftdev.postbud.Utils.Parcel;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AddParcelActivity extends AppCompatActivity {
     @BindView(R.id.et_description)
     EditText etDesc;
     @BindView(R.id.et_location)
     EditText etLocation;
     @BindView(R.id.et_ordered_by)
     EditText etOrderedBy;
     @BindView(R.id.et_priority)
     EditText etPriority;
     @BindView(R.id.sp_status)
     Spinner spStatus;
     @BindView(R.id.et_destination)
     EditText etDest;
     @BindView(R.id.et_origin)
     EditText etOrigin;
     @BindView(R.id.et_weight)
     EditText etWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcel);
        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);
    }


     public void saveParcel() {
          CollectionReference parcelReference = FirebaseFirestore.getInstance().collection("parcels");

          parcelReference.add(new Parcel(
                          "123",
                          etLocation.getText().toString().trim(),
                          etOrigin.getText().toString().trim(),
                          etDest.getText().toString().trim(),
                          etOrderedBy.getText().toString().trim(),
                          etDesc.getText().toString().trim(),
                          Double.parseDouble(String.valueOf(etWeight.getText())),
                          Timestamp.now(),
                          Integer.parseInt(etPriority.getText().toString()),
                          spStatus.getSelectedItem().toString().trim()
                  )
          ).addOnSuccessListener(aVoid -> {
               Toast.makeText(AddParcelActivity.this, "Parcel saved to Firebase", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);
          })
                  .addOnFailureListener(e -> {
                       Toast.makeText(AddParcelActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                       Timber.e("AddParcelActivity");
                  });
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_parcel_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveParcel:
                // TODO this has a problem saving the object for some reason... coming back to it
                saveParcel();
                Toast.makeText(this, "Saving Parcel", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}