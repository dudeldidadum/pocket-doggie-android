package com.n3rditorium.pocketdoggie.dog.deeds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.n3rditorium.pocketdoggie.R;
import com.n3rditorium.pocketdoggie.buisness.DeedBO;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Deed;
import com.n3rditorium.pocketdoggie.transitions.FabTransform;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddADeedActivity extends AppCompatActivity {

   public static final String EXTRA_DOG_REFERENCE = "extras.dog.reference";
   @BindView (R.id.container)
   View container;
   @Inject
   DeedBO deedBO;
   @BindView (R.id.edt_comment)
   EditText description;
   @BindView (R.id.group_in_or_outside)
   RadioGroup groupInOrOutside;
   @BindView (R.id.group_pee_or_poop)
   RadioGroup groupPeeOrPoop;
   @BindView (R.id.txt_time)
   TextView txtTime;

   private String dogReference;
   private boolean isDismissing = false;

   public static Intent getIntent(Context context, String dogReference) {
      Intent intent = new Intent(context, AddADeedActivity.class);
      intent.putExtra(EXTRA_DOG_REFERENCE, dogReference);

      return intent;
   }

   @OnClick (R.id.btn_cancel)
   public void dismiss(View view) {
      isDismissing = true;
      setResult(Activity.RESULT_CANCELED);
      finishAfterTransition();
   }

   @Override
   public void onBackPressed() {
      dismiss(null);
   }

   @SuppressWarnings ("unused")
   @OnClick (R.id.btn_ok)
   public void save() {
      if (!validateInputs()) {
         Toast.makeText(this, "please validate all you intputs", Toast.LENGTH_SHORT)
               .show();
         return;
      }
      Deed deed = new Deed().setDescription(description.getText()
            .toString())
            .setTimestamp(System.currentTimeMillis())
            .setInside(getInOrOutside(groupInOrOutside.getCheckedRadioButtonId()))
            .setDogReference("-KKZqKiehjVvTyoJRpU7")
            .setType(getDeedType(groupPeeOrPoop.getCheckedRadioButtonId()));
      deedBO.save(deed, "-KKZqKiehjVvTyoJRpU7");

      setResult(Activity.RESULT_OK);
      finishAfterTransition();
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Injector.getAppComponent()
            .inject(this);
      setContentView(R.layout.add_a_deed_activity);
      ButterKnife.bind(this);
      FabTransform.setup(this, container);

      long timestamp = System.currentTimeMillis();
      String time = DateUtils.formatDateTime(this, timestamp, DateUtils.FORMAT_SHOW_TIME);
      String day = DateUtils.formatDateTime(this, timestamp, DateUtils.FORMAT_SHOW_DATE);

      String text = time + " - " + day;
      txtTime.setText(text);
      dogReference = getIntent().getExtras()
            .getString(EXTRA_DOG_REFERENCE, "Mgr59WZhxrRyyNtNqd2aZgdIudd2");
   }

   private int getDeedType(int checkedViewId) {
      if (checkedViewId == R.id.chk_pee) {
         return Deed.Type.PEE;
      }
      return Deed.Type.POOP;
   }

   private boolean getInOrOutside(int checkedViewId) {
      return checkedViewId == R.id.chk_inside;
   }

   private boolean validateInputs() {
      // TODO validate inputs
      return true;
   }
}
