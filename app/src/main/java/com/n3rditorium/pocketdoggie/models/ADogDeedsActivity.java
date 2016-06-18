package com.n3rditorium.pocketdoggie.models;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.n3rditorium.pocketdoggie.R;
import com.n3rditorium.pocketdoggie.buisness.BuisnessObject;
import com.n3rditorium.pocketdoggie.buisness.DeedBO;
import com.n3rditorium.pocketdoggie.dog.deeds.AddADeedActivity;
import com.n3rditorium.pocketdoggie.dog.deeds.DeedsItemAdapter;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.transitions.FabTransform;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ADogDeedsActivity extends AppCompatActivity {

   public static final int RC_NEW_DOG_DEED = 1707;
   @Inject
   DeedBO deedBO;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Injector.getAppComponent()
            .inject(this);
      setContentView(R.layout.dog_deeds_activity);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent = new Intent(ADogDeedsActivity.this, AddADeedActivity.class);

            FabTransform.addExtras(intent,
                  ContextCompat.getColor(ADogDeedsActivity.this, R.color.colorAccent),
                  R.drawable.ic_add_light);
            ActivityOptions options =
                  ActivityOptions.makeSceneTransitionAnimation(ADogDeedsActivity.this, fab,
                        getString(R.string.transition_add_a_deed));
            startActivityForResult(intent, RC_NEW_DOG_DEED, options.toBundle());
         }
      });
            fetchData();
//      setupRecycler(Deed.mockList());
   }

   private void fetchData() {
      deedBO.loadAll(new BuisnessObject.OnDataCallback<Deed>() {
         @Override
         public void noData(Throwable throwable) {
            Log.e(ADogDeedsActivity.class.getSimpleName(), throwable.getMessage(), throwable);
         }

         @Override
         public void onItem(Deed deed) {
            // not relevant here
         }

         @Override
         public void onList(List<Deed> deeds) {
            setupRecycler(deeds);
         }
      }, "-KKZqKiehjVvTyoJRpU7");
   }

   private void setupRecycler(List<Deed> deeds) {
      if (deeds == null || deeds.isEmpty()) {
         Log.i(getClass().getSimpleName(), "no items available");
         // TODO show empty view!
         return;
      }

      DeedsItemAdapter adapter = new DeedsItemAdapter(this, deeds);
      RecyclerView recyclerView = ButterKnife.findById(this, R.id.list);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(adapter);
   }
}
