package com.n3rditorium.pocketdoggie.dog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.n3rditorium.pocketdoggie.R;
import com.n3rditorium.pocketdoggie.buisness.DogBO;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Dog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DogActivity extends AppCompatActivity {

   @Inject
   DogBO dogBO;
   @BindView (R.id.edt_description)
   EditText edtDescription;
   @BindView (R.id.edt_height)
   EditText edtHeight;
   @BindView (R.id.edt_name)
   EditText edtName;
   @BindView (R.id.edt_weight)
   EditText edtWeight;
   //   @BindView (R.id.edt_timestamp)
   //   EditText edtTimestamp;

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.dog_profile_menu, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      if (item.getItemId() == R.id.action_save) {
         Dog penny = new Dog().setName("Penny")
               .setDescription("Bayerischer Gebirgsschweißhund und Altdeutscher Hirtehund Mix")
               .setGender(Gender.FEMALE)
               .setBirthday(1456527600000L)
               .setWeight(10000)
               .setHeight(37);
         dogBO.save(penny, "Mgr59WZhxrRyyNtNqd2aZgdIudd2");
         return true;
      }
      return super.onOptionsItemSelected(item);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.dog_activity);
      Injector.getAppComponent()
            .inject(this);
      ButterKnife.bind(this);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
   }

   @SuppressWarnings ("unused")
   private Dog buildDogFromInput() {
      // TODO add validations!!!!
      return new Dog().setName(edtName.getEditableText()
            .toString())
            .setDescription(edtDescription.getEditableText()
                  .toString())
            .setGender(Gender.FEMALE)
            .setBirthday(1456527600000L)
            .setWeight(Integer.parseInt(edtWeight.getEditableText()
                  .toString()))
            .setHeight(Integer.parseInt(edtHeight.getEditableText()
                  .toString()));
   }
}
