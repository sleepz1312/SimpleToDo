package sg.edu.rp.c346.id21024625.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTodo;
    Button btnAdd;
    Button btnClear;
    ListView lvTodo;
    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;
    Spinner spn;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTodo = findViewById(R.id.todo);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        lvTodo = findViewById(R.id.lvTodo);
        spn = findViewById(R.id.spntask);
        btnDel = findViewById(R.id.btnDelete);

        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodo);

        lvTodo.setAdapter(aaTodo);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alTodo.removeAll(alTodo);
                aaTodo.notifyDataSetChanged();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        String spinnerItems = spn.getSelectedItem().toString();
                        Toast.makeText(MainActivity.this, "Add Task", Toast.LENGTH_SHORT).show();
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String tasks = etTodo.getText().toString();
                                alTodo.add(tasks);
                                aaTodo.notifyDataSetChanged();
                            }
                        });
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        etTodo.setHint(R.string.enter_task);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Delete Task", Toast.LENGTH_SHORT).show();
                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (alTodo.size() > 0) {
                                    int pos = Integer.parseInt(etTodo.getText().toString());
                                    if (pos + 1 > alTodo.size()) {
                                        Toast.makeText(getApplicationContext(), R.string.wrong_index, Toast.LENGTH_SHORT).show();
                                    } else {
                                        alTodo.remove(pos);
                                        aaTodo.notifyDataSetChanged();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(),R.string.list_empty,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        btnDel.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTodo.setHint(R.string.delete_task);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}