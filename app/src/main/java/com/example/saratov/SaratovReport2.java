package com.example.saratov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Category;
import io.swagger.client.model.Complaint;
import io.swagger.client.model.ComplaintDraft;

public class SaratovReport2 extends AppCompatActivity {

    // TODO: восстановить обработку жалобы после починки сервера
    private ComplaintDraft complaint;
    private TextView textView;
    private DefaultApi API;

    ArrayList<String> categoryTitleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.saratov_report_2);
        complaint = (ComplaintDraft) getIntent().getSerializableExtra("complaint");
        API = new DefaultApi();
        textView = findViewById(R.id.problemType);

        //TODO: вернуть после починки сервера
        /*try {
            complaint = API.detectComplaintCategory(complaint);
            textView.setText(complaint.getCategory().getCategory());
        } catch (ApiException e) {
            //textView.setError("Не удалось определить тип проблемы");
        }*/

        //TODO: вернуть после починки сервера
        /*try {
            categoryList = API.getAllComplaintCategories();
            for (Category category : categoryList) {
                categoryTitleList.add(category.getCategory());
            }
        } catch (ApiException e) {
            textView.setError("Не удалось загрузить список типов.");
        }*/

        categoryTitleList.add("ДТП");
        categoryTitleList.add("Обрушение здания");
        categoryTitleList.add("Отключение водоснабжения");
        categoryTitleList.add("Отключение электричества");
        categoryTitleList.add("Прорыв трубы");
        categoryTitleList.add("Упавшее дерево");
        categoryTitleList.add("Ямы на дорогах");

        // Создаем прокручиваемый список типов проблем
        ListView problemTypeList = findViewById(R.id.problemTypeList);

        // Создаем список TextView с названиями категорий проблем
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        categoryTitleList);

        // Привязываем к списку на странице адаптер с данными
        problemTypeList.setAdapter(arrayAdapter);

        // Добавляем обработчик события клика по типу проблемы (categoryTitleList)
        problemTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                System.out.println("Item clicked");
                System.out.println(categoryTitleList.get(position));
                textView.setText(categoryTitleList.get(position));

                //TODO: вернуть после просмотра метода setCategory
                //complaint.setCategory(categoryList.get(position));
            }
        });
    }

    //Обработчик клика по кнопке "Изменить"
    public void showProblemTypeList(View view) {
        ListView problemTypeList = findViewById(R.id.problemTypeList);
        problemTypeList.setVisibility(View.VISIBLE);
    }

    // Метод создания стрелки перехода на следующую страницу
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigator_report, menu);
        return super.onCreateOptionsMenu(menu);

    }

    // Обработка нажатия на стрелку перехода
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ClearAllErrors();
        // Проверяем, на какую кнопку верхнего меню мы нажали
        switch (item.getItemId()) {
            case R.id.action_next:
                return GoToNextPage();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: после починки сервера вернуть работу с API
    public boolean GoToNextPage() {
        try {
            //API.updateomplaintDraft(complaint);
            Intent intent = new Intent(this, SaratovReport3.class);
            //intent.putExtra("complaint", complaint);
            startActivity(intent);
            return true;
        } /*catch (ApiException e) {
            textView.setError("Произошла ошибка. Код ошибки: " + e.getMessage());
            return false;
        } */ catch (RuntimeException re) {
            textView.setError("Произошла ошибка. Код ошибки: " + re.getMessage());
            return false;
        }
    }

    private void ClearAllErrors() {
        textView.setError(null);
    }
}
