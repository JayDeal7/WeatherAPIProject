package com.example.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import javax.persistence.PersistenceProperty;
import java.awt.*;

@Route("")
public class MainView extends VerticalLayout {

    private Repository repository;
    private TextField cityName = new TextField("Location Name");

    private TextField weatherDescription = new TextField("Weather Description");
    private TextField temperature = new TextField("Temperature");
    private TextField humidity = new TextField("humidity");

    private Grid<WeatherLayout> grid = new Grid<>(WeatherLayout.class);
    private Binder<WeatherLayout> binder = new Binder<>(WeatherLayout.class);

    public MainView(Repository repository) {

        this.repository = repository;

        grid.setColumns("cityName","weatherDescription", "temperature", "humidity");
        add(getForm(), grid);
        refreshGrid();

    }

    private Component getForm() {
        cityName.setId("LocationName");
        var layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);
        var addButton = new Button("Add");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.setId("LocationButton");
        layout.add(cityName,addButton);

        weatherDescription.setVisible(false);
        temperature.setVisible(false);
        humidity.setVisible(false);


        binder.bindInstanceFields(this);
        addButton.addClickListener(click -> {
            try{

                Weather w = new Weather();
                w.getWeatherData(cityName.getValue());
                weatherDescription.setValue(w.getDescription());
                temperature.setValue(w.getTemperature());
                humidity.setValue(w.getHumidity());

                var weatherComponent = new WeatherLayout();
                binder.writeBean((weatherComponent));
                repository.save(weatherComponent);
                refreshGrid();
            } catch (ValidationException e){
                //
            }
        });

        return layout;
    }

    private void refreshGrid() {
        grid.setItems(repository.findAll());
    }
}
