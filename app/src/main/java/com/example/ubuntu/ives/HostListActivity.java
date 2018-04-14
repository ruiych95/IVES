package com.example.ubuntu.ives;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HostListActivity extends ListActivity
{
    ArrayList<DTO_Host> hostDTOList;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostlist);
        Toast.makeText(getApplicationContext(), "Done.",
                Toast.LENGTH_LONG).show();

        ListView listview = findViewById(android.R.id.list);
        hostDTOList = getIntent().getParcelableArrayListExtra("hostDTO");
        ArrayList<String> listOfHosts = new ArrayList<>();
        for(DTO_Host hostDTO : hostDTOList)
        {
            listOfHosts.add(hostDTO.getIp());
        }

        /*ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, listOfHosts);*/
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2,
                android.R.id.text1, hostDTOList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(hostDTOList.get(position).getIp());
                text2.setText(hostDTOList.get(position).getName());
                return view;
            }
        };
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DTO_Host dto = (DTO_Host)parent.getItemAtPosition(position);
                String ip = dto.getIp();
                for( DTO_Host hostDTO : hostDTOList)
                {
                    if(ip.equals(hostDTO.getIp()))
                    {
                        Intent hostDetail = new Intent(getApplicationContext(), HostDetailActivity.class);
                        hostDetail.putExtra("hostDTO", hostDTO);
                        startActivity(hostDetail);
                    }
                }
            }
        });
    }
}
