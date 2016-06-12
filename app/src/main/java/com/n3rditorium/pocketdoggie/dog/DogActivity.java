package com.n3rditorium.pocketdoggie.dog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.n3rditorium.pocketdoggie.R;
import com.n3rditorium.pocketdoggie.buisness.DogBO;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Dog;
import com.n3rditorium.pocketdoggie.models.Metric;

import javax.inject.Inject;

public class DogActivity extends AppCompatActivity {

   @Inject
   DogBO dogBO;

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
               .setWeight(new Metric().setValue(9000))
               .setHeight(new Metric().setValue(37));
         dogBO.save("Mgr59WZhxrRyyNtNqd2aZgdIudd2", penny);
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
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  .setAction("Action", null)
                  .show();
         }
      });
   }
}
