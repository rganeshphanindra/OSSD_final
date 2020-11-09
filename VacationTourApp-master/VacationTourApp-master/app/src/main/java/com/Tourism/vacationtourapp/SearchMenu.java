package com.Tourism.vacationtourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchMenu extends AppCompatActivity {
    ListView listView;
    int images[] = {R.drawable.mughal_gardens, R.drawable.img3, R.drawable.recentimage2, R.drawable.nainital,R.drawable.recentimage1};
    String nameList[] = {"MughalGardens", "Agra", "Ooty", "Nainital","AM Lake"};
    String desc[] = {"This is MughalGarden. Spread over a vast expanse of fifteen acres, Mughal Gardens has often been portrayed, and deservedly so, as the soul of the Presidential Palace. The Mughal Gardens draw its inspiration from the Mughal Gardens of Jammu and Kashmir, the gardens around the Taj Mahal and even miniature paintings of India and Persia.", "Located on the banks of River Yamuna in Uttar Pradesh, Agra is a popular tourist destination as it is home to one of the seven wonders of the world, the Taj Mahal. It is a sneak peek into the architectural history and legacy of the Mughal empire with two other UNESCO World Heritage Sites Agra Fort and Fatehpur Sikri.", "Ooty (short for Udhagamandalam) is a resort town in the Western Ghats mountains, in southern India's Tamil Nadu state. Founded as a British Raj summer resort, it retains a working steam railway line. Other reminders of its colonial past include Stone House, a nineteenth-century residence.", "Nainital is a Himalayan resort town in the Kumaon region of India’s Uttarakhand state, at an elevation of roughly two thousand meters. Formerly a British hill station, it’s set around Nainital Lake, a popular boating site with Naina Devi Hindu Temple on its north shore.", "Jaisamand Lake is a true human-made wonder offering all the form of natural beauty. Starting from the hilly surroundings, the two palaces, Hawa Mahal and Ruthi Rani Ka Mahal, Jaisamand Wildlife sanctuary, Jaisamand Island resort, all offer an exquisite and luxurious experience. The Jaisamand Lake tend to unfold a new chapter of Udaipur."};

    List<ItemsModel> listItems = new ArrayList<>();

    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
        listView = findViewById(R.id.listview);

        for (int i = 0; i < nameList.length; i++) {
            ItemsModel itemsModel = new ItemsModel(nameList[i], desc[i], images[i]);

            listItems.add(itemsModel);
        }

        customAdapter = new CustomAdapter(listItems, this);
        listView.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<ItemsModel> itemsModelList;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelList, Context context) {
            this.itemsModelList = itemsModelList;
            this.itemsModelListFiltered = itemsModelList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_items,null);

            ImageView imageView123 = view.findViewById(R.id.imageView12);
            TextView itemName = view.findViewById(R.id.itemName);
            TextView itemDesc = view.findViewById(R.id.itemDesc);


            imageView123.setImageResource(itemsModelListFiltered.get(position).getImage());
            itemName.setText(itemsModelListFiltered.get(position).getName());
            //itemDesc.setText(itemsModelListFiltered.get(position).getDesc());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SearchMenu.this,PlaceViewer.class).putExtra("item",itemsModelListFiltered.get(position)));
                }
            });


            return view;
        }

        @Override
        public Filter getFilter() {

            Filter filter=new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResuts = new FilterResults();
                    if(constraint == null|| constraint.length()==0){
                        filterResuts.count = itemsModelList.size();
                        filterResuts.values = itemsModelList;
                    }
                    else{
                        String searchStr = constraint.toString().toLowerCase();

                        List<ItemsModel> resultData = new ArrayList<>();
                        for (ItemsModel itemsModel:itemsModelList){
                            if(itemsModel.getName().contains(searchStr)||itemsModel.getDesc().contains(searchStr)){
                                resultData.add(itemsModel);

                            }
                            filterResuts.count = resultData.size();
                            filterResuts.values = resultData;
                        }
                    }

                    return filterResuts;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }
}