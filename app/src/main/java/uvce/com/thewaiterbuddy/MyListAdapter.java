package uvce.com.thewaiterbuddy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Venkatesh Belavadi on 19-Mar-18.
 */

public class MyListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private ArrayList<String> name=new ArrayList<>();
    private ArrayList<Integer> quantity=new ArrayList<>();
    private ArrayList<Double> cost=new ArrayList<>();

    public MyListAdapter(Activity context,ArrayList<String> name,ArrayList<Integer> quantity, ArrayList<Double> cost)
    {
        super(context,R.layout.checkout_list,name);

        this.context=context;
        this.name=name;
        this.quantity=quantity;
        this.cost=cost;

    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.checkout_list,null,true);

        TextView foodName= rowView.findViewById(R.id.name);
        TextView foodQuantity= rowView.findViewById(R.id.quantity);
        TextView foodCost=rowView.findViewById(R.id.cost);

        foodName.setText(name.get(position));
        foodQuantity.setText(""+quantity.get(position));
        foodCost.setText(""+cost.get(position));

        return rowView;
    }

}
