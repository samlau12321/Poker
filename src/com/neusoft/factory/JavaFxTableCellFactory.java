package com.neusoft.factory;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class JavaFxTableCellFactory {
    private static JavaFxTableCellFactory javaFxTableCellFactory = null;
    private JavaFxTableCellFactory(){
    }
    public static JavaFxTableCellFactory getJavaFxTableCellFactory(){
        if (javaFxTableCellFactory == null){
            javaFxTableCellFactory = new JavaFxTableCellFactory();
        }

        return javaFxTableCellFactory;
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> tableCheckBoxColumn(
            Callback<Integer, ObservableValue<Boolean>> paramCallback) {
        return tableCheckBoxColumn(paramCallback, null);
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> tableCheckBoxColumn(
            final Callback<Integer, ObservableValue<Boolean>> getSelectedProperty,
            final StringConverter<T> converter) {
        return new Callback<TableColumn<S, T>, TableCell<S, T>>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> paramTableColumn) {
                return new CheckBoxTableCell<S, T>(getSelectedProperty, converter);
            }
        };
    }
}
