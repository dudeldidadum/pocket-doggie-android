package com.n3rditorium.pocketdoggie.dog.deeds;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.n3rditorium.pocketdoggie.R;
import com.n3rditorium.pocketdoggie.models.ADogDeedsActivity;
import com.n3rditorium.pocketdoggie.models.Deed;
import com.n3rditorium.pocketdoggie.transitions.FabTransform;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeedsItemAdapter extends RecyclerView.Adapter<DeedsItemAdapter.ViewHolder> {

   public class ViewHolder extends RecyclerView.ViewHolder {

      @BindView (R.id.img_type)
      ImageView icon;
      @BindView (R.id.btn_menu)
      View popup;
      @BindView (R.id.txt_subtitle)
      TextView subtitle;
      @BindView (R.id.txt_title)
      TextView title;

      public ViewHolder(View itemView) {
         super(itemView);
         ButterKnife.bind(this, itemView);
      }
   }

   private static final int TYPE_HEADER = 0;
   private static final int TYPE_ITEM = 1;
   private ADogDeedsActivity activity;
   private List<Deed> items;

   public DeedsItemAdapter(ADogDeedsActivity activity, List<Deed> items) {
      this.items = items;
      Collections.reverse(this.items);
      this.activity = activity;
   }

   public void addItem(Deed item) {
      items.add(0, item);
      notifyDataSetChanged();
   }

   @Override
   public int getItemCount() {
      return items.size();
   }

   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
      Deed item = items.get(position);
      holder.title.setText(item.formatTimestamp(getContext(holder.itemView)));
      holder.subtitle.setText(item.getDescription());
      if (item.getType() == Deed.Type.PEE) {
         holder.icon.setImageResource(R.drawable.ic_umbrella);
         holder.icon.setColorFilter(ContextCompat.getColor(activity, R.color.pee_yellow));
      } else {
         holder.icon.setImageResource(R.drawable.ic_menu_poop);
         holder.icon.setColorFilter(ContextCompat.getColor(activity, R.color.poop_brown));
      }
      holder.popup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(activity, AddADeedActivity.class);

            FabTransform.addExtras(intent, ContextCompat.getColor(activity, android.R.color.white),
                  R.drawable.ic_dots_vertical);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, v,
                  activity.getString(R.string.transition_add_a_deed));
            activity.startActivityForResult(intent, ADogDeedsActivity.RC_NEW_DOG_DEED,
                  options.toBundle());
            // TODO show popup menu
         }
      });


      ViewGroup.MarginLayoutParams layoutParams =
            (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
      if (position == 0) {
         layoutParams.topMargin = getContext(holder.itemView).getResources()
               .getDimensionPixelSize(R.dimen.activity_vertical_margin);
         layoutParams.bottomMargin = (getContext(holder.itemView).getResources()
               .getDimensionPixelSize(R.dimen.activity_vertical_margin) / 2);
      } else if (position == getItemCount() - 1) {
         layoutParams.topMargin = 0;
         layoutParams.bottomMargin = getContext(holder.itemView).getResources()
               .getDimensionPixelSize(R.dimen.activity_vertical_margin);
      } else {
         layoutParams.topMargin = 0;
         layoutParams.bottomMargin = (getContext(holder.itemView).getResources()
               .getDimensionPixelSize(R.dimen.activity_vertical_margin) / 2);
      }
   }

   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(getContext(parent))
            .inflate(R.layout.deeds_list_item, parent, false));
   }

   private Context getContext(View view) {
      if (view == null) {
         return null;
      }
      return view.getContext();
   }
}
