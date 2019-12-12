package com.tsa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.Scanner;


public class WorkWithDB extends Application {
    private TextArea informationBlock;
    private TextArea inputBlock;
    private TextArea inputTable;
    private DataBase dataBase;
    private Button tableSetButton;
    private Button myStringButton;
    int typeOfRequiredTable = 0;
    private String url;
    private int tableForDelete=0;

    public void start(Stage stage) {

        StackPane root = new StackPane();
        informationBlock = new TextArea("Введите Start, чтобы создать Базу данных, таблицы и заполнить их начальными значениями ");
        inputBlock = new TextArea();
        inputTable = new TextArea();
        Image imageDB = null;
        myStringButton = new Button("set string");
        tableSetButton = new Button("Table");
        Button backToMainScreen = new Button("Main screen");

        Button confirmButton = new Button("Confirm");
        File image = new File("/Users/tessergey/Downloads/asdasd.jpg");
        File fileDB = new File("/Users/tessergey/Downloads/DB_2.png");

        try {
            String localUrl = fileDB.toURI().toURL().toString();
            imageDB = new Image(localUrl, 70, 70, false, false);
        } catch (MalformedURLException e ) {

        }

        try {
            url = image.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            System.out.println("Bad in url load image");
        }

        BackgroundImage myBI = new BackgroundImage(new Image(url, 1000, 1000, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(myBI));
        confirmButton.setMinSize(200, 50);
        myStringButton.setMinSize(200, 50);
        tableSetButton.setMinSize(200,50);

        backToMainScreen.setMinSize(100, 50);

        tableSetButton = new Button("Table");

        root.setAlignment(Pos.CENTER);

        //Установка отступов для каждого элемента
        StackPane.setMargin(informationBlock, new Insets(100, 100, 200, 100.0));
        StackPane.setMargin(inputBlock, new Insets(700, 100, 200, 100));
        StackPane.setMargin(confirmButton, new Insets(800, 200, 200, 0));
        StackPane.setMargin(myStringButton, new Insets(800,-200, 200, 0));
        StackPane.setMargin(inputTable, new Insets(800, 250, 200, 750.0));
        StackPane.setMargin(tableSetButton, new Insets(800, 125, 200, 725));
        ImageView imageView = new ImageView(imageDB);
        StackPane.setMargin(imageView, new Insets(650, 150, 350, 850));
        StackPane.setMargin(backToMainScreen, new Insets(700,800,400,200));

        //Кнопка для возврата на главный экран
        backToMainScreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                informationBlock.setText("");
                whatNext();
            }
        });

        //Обработчик на кнопку Confirm
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double value = imageView.getRotate();
                imageView.setRotate(value + 20);

                String answer = inputBlock.getText();
                if (answer.equalsIgnoreCase("Start")) {
                    answerStart();
                    whatNext();
                } else if (answer.equalsIgnoreCase("DropDB")) {
                    answerDropDB();
                    informationBlock.setText("Конец");
                } else if (answer.equalsIgnoreCase("SelectAll")) {
                    answerSelectAll();
                } else if (answer.equalsIgnoreCase("OK")) {
                    whatNext();
                } else if (answer.equalsIgnoreCase("Insert")) {
                    informationBlock.setText("Введите номер таблицы для вставки и нажмите соотвествующую кнопку");
                    typeOfRequiredTable = 1;
                } else if (answer.equalsIgnoreCase("Search")) {
                    informationBlock.setText("Введите номер таблицы для поиска и нажмите соотвествующую кнопку");
                    typeOfRequiredTable = 2;
                } else if (answer.equalsIgnoreCase("Clear")) {
                    informationBlock.setText("Введите номер таблицы из которой нужно удалить все записи и нажмите соответствующую кнопку");
                    typeOfRequiredTable = 3;
                } else if (answer.equalsIgnoreCase("ClearAll")) {
                    answerClearReallyAllTables();
                } else if (answer.equalsIgnoreCase("Delete")) {
                    inputBlock.setText("");
                    informationBlock.setText("1. Delete constant\n2. Delete my string");
                } else if (answer.equalsIgnoreCase("Delete constant")) {
                    informationBlock.setText("Введите номер таблицы из которой нужно удалить запись");
                    typeOfRequiredTable = 4;
                } else if (answer.equalsIgnoreCase("Delete my string")) {
                    informationBlock.setText("Введите номер таблицы из которой нужно удалить запись");
                    typeOfRequiredTable = 5;
                } else if (answer.equalsIgnoreCase("Update")) {
                    informationBlock.setText("Введите номер таблицы из которой нужно обновить запись");
                    typeOfRequiredTable = 6;
                }
            }
        });

        //Обработчик на кнопку Table
        tableSetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double value = imageView.getRotate();
                imageView.setRotate(value + 20);

                String answer = inputTable.getText();
                int a = Integer.parseInt(answer);
                helpForHandler(a);
            }
        });

        //Обработчик на кнопку отправки строки для удаления от пользователя
        myStringButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double value = imageView.getRotate();
                imageView.setRotate(value + 20);
                String name = inputBlock.getText();
                answerDeleteMyString(name);
            }
        });

        //Добавление всех элементов на панель
        root.getChildren().addAll(informationBlock,confirmButton,inputBlock,tableSetButton,inputTable,myStringButton,imageView, backToMainScreen);

        //Параметры сцены
        Scene scene = new Scene(root, 250, 250);
        stage.setScene(scene);
        stage.setTitle("🖤DataBase‼️🖤😈");
        stage.setWidth(1000);
        stage.setHeight(1000);
        stage.show();
    }

    private void answerUpdate() {

    }

    //В зависимости от типа запроса Delete, Insert.. метод вызывает необходимые функции
    private void helpForHandler(int i) {

        if (typeOfRequiredTable == 1) {
            switch (i) {
                case 1:
                    answerInsert(1);
                    break;
                case 2:
                    answerInsert(2);
                    break;
                case 3:
                    answerInsert(3);
                    break;
                case 4:
                    answerInsert(4);
                    break;
                default:
                    informationBlock.setText("Таблицы с таким номером нет");
                    break;
            }
        } else if (typeOfRequiredTable == 2) {
            switch (i) {
                case 1:
                    answerSearch(1);
                    break;
                case 2:
                    answerSearch(2);
                    break;
                case 3:
                    answerSearch(3);
                    break;
                case 4:
                    answerSearch(4);
                    break;
                default:
                    informationBlock.setText("Таблицы с таким номером нет");
                    break;
            }
        } else if (typeOfRequiredTable == 3) {
            switch (i) {
                case 1:
                    answerClearAll(1);
                    break;
                case 2:
                    answerClearAll(2);
                    break;
                case 3:
                    answerClearAll(3);
                    break;
                case 4:
                    answerClearAll(4);
                    break;
                default:
                    informationBlock.setText("Таблицы с таким номером нет");
                    break;

            }
        } else if (typeOfRequiredTable == 4) {
            switch (i) {
                case 1:
                    answerDeleteConstant(1);
                    break;
                case 2:
                    answerDeleteConstant(2);
                    break;
                case 3:
                    answerDeleteConstant(3);
                    break;
                case 4:
                    answerDeleteConstant(4);
                    break;
                default:
                    informationBlock.setText("Таблицы с таким номером нет");
                    break;
            }
        } else if (typeOfRequiredTable ==5) {
            switch (i) {
                case 1:
                    answerDeleteMyString(1);
                    break;
                case 2:
                    answerDeleteMyString(2);
                    break;
                case 3:
                    answerDeleteMyString(3);
                    break;
                case 4:
                    answerDeleteMyString(4);
                    break;
                default:
                    informationBlock.setText("Таблицы с таким номером нет");
                    break;
            }
        } else if (typeOfRequiredTable==6) {
            switch (i) {
                case 1:
                    answerUpdate(1);
                    break;
                case 2:
                    answerUpdate(2);
                    break;
                case 3:
                    answerUpdate(3);
                    break;
                case 4:
                    answerUpdate(4);
                    break;
                default:
                    informationBlock.setText("Таблицы с таким номером нет");
                    break;
            }
        }
    }

    //Метод вызывает методы БД по обновлению кортежа
    private void answerUpdate(int table) {

        try {
            switch (table) {
                case 1:
                    dataBase.updateTable1(dataBase.nameDataBase,table);
                    informationBlock.setText("Данные в таблице были обновлены");
                    break;
                case 2:
                    dataBase.updateTable2(dataBase.nameDataBase,table);
                    informationBlock.setText("Данные в таблице были обновлены");
                    break;
                case 3:
                    dataBase.updateTable3(dataBase.nameDataBase,table);
                    informationBlock.setText("Данные в таблице были обновлены");
                    break;
                case 4:
                    dataBase.updateTable4(dataBase.nameDataBase,table);
                    informationBlock.setText("Данные в таблице были обновлены");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Bad in update");
        }


    }

    //Метод предлагает ввести ключевое слово для удаления
    private void answerDeleteMyString(int table) {
        informationBlock.setText("Введите ключевое слово для удаления");
        inputBlock.setText("");
        tableForDelete=table;
    }

    //Метод вызывает метод базы данный для удаления записи по текстовому полю, полученному от пользователя
    private void answerDeleteMyString(String name) {
        try {
            switch (tableForDelete) {
                case 1:
                    dataBase.deleteTable1(dataBase.nameDataBase,name);
                    informationBlock.setText("Из таблицы " + tableForDelete+" была удалена запись с именем " + name);
                    break;
                case 2:
                    dataBase.deleteTable2(dataBase.nameDataBase,name);
                    informationBlock.setText("Из таблицы " + tableForDelete+" была удалена запись с именем " + name);
                    break;
                case 3:
                    dataBase.deleteTable3(dataBase.nameDataBase,name);
                    informationBlock.setText("Иа таблицы " + tableForDelete+" была удалена запись с именем " + name);
                    break;
            }

        }catch (SQLException e) {
            System.out.println("Bad in my string delete");
        }
    }

    //Метод вызывает метод базы данных для удаления записи по заранее заданному текстовому не ключевому полю
    private void answerDeleteConstant(int table) {
        try {
            switch (table) {
                case 1:
                    dataBase.deleteTable1(dataBase.nameDataBase, "Petr");
                    informationBlock.setText("Строка с именем Petr удалена из таблицы " + table);
                    inputBlock.setText("");
                    break;
                case 2:
                    dataBase.deleteTable2(dataBase.nameDataBase, "Around the world");
                    informationBlock.setText("Строка с именем Around the world удалена из таблицы " + table);
                    inputBlock.setText("");
                    break;
                case 3:
                    dataBase.deleteTable3(dataBase.nameDataBase, "Pedro");
                    informationBlock.setText("Строка с именем Pedro удалена из таблицы " + table);
                    inputBlock.setText("");
                    break;
                case 4:
                    dataBase.deleteTable4(dataBase.nameDataBase, 100);
                    informationBlock.setText("Строка 100 удалена из таблицы " + table);
                    inputBlock.setText("");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Bad in const delete");
        }
    }

    //Метод вызывает метод базы данных для отчистки данных всех таблиц
    private void answerClearReallyAllTables() {
        try {
            dataBase.deleteAllElementTable4(dataBase.nameDataBase);
        } catch (SQLException e) {
            System.out.println("Bad in really clear all");
        }
    }

    //Метод вызывает метод базы данных для отчистки данных конкретной таблицы
    private void answerClearAll(int table) {
        try {
            switch (table) {
                case 1:
                    informationBlock.setText("Данные удалены из таблицы " + table);
                    dataBase.deleteDataFromTable1(dataBase.nameDataBase);
                    inputTable.setText("");
                    break;
                case 2:
                    informationBlock.setText("Данные удалены из таблицы " + table);
                    dataBase.deleteAllElementTable2(dataBase.nameDataBase);
                    inputTable.setText("");
                    break;
                case 3:
                    informationBlock.setText("Данные удалены из таблицы " + table);
                    dataBase.deleteAllElementTable3(dataBase.nameDataBase);
                    inputTable.setText("");
                    break;
                case 4:
                    informationBlock.setText("Данные удалены из таблицы " + table);
                    dataBase.deleteAllElementTable4(dataBase.nameDataBase);
                    inputTable.setText("");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Bad in clear");
        }

    }

    //Метод вызывает метод базы данных для поиска по заранее задонному текстовому полю
    private void answerSearch(int table) {
        String name = "";
        try {
            switch (table) {
                case 1:
                    name = "Kate";
                    informationBlock.setText(dataBase.searchTable1(dataBase.nameDataBase, name));
                    inputTable.setText("");
                    break;
                case 2:
                    name = "Deep learning";
                    informationBlock.setText(dataBase.searchTable2(dataBase.nameDataBase, name));
                    inputTable.setText("");
                    break;
                case 3:
                    name = "Pedro";
                    informationBlock.setText(dataBase.searchTable3(dataBase.nameDataBase, name));
                    inputTable.setText("");
                    break;
                case 4:
                    int nameInt = 100;
                    informationBlock.setText(dataBase.searchTable4(dataBase.nameDataBase, nameInt));
                    inputTable.setText("");
                    break;
            }

        } catch (SQLException e) {
            System.out.println("Bad in search");
        }
    }

    //Метод вызывает метод базы данных для вставки заранее заданных данных в таблицу
    private void answerInsert(int table) {
        inputBlock.setText("");
        try {
            switch (table) {
                case 1:
                    dataBase.insertTable1(dataBase.nameTable1, dataBase.nameDataBase,
                            6, "8912121212", "Pupok", "asdf@mail.ru",
                            40, 1);
                    informationBlock.setText("Данные были добавлены:\n" + dataBase.selectFromTable1(dataBase.nameDataBase, dataBase.nameTable1));
                    inputTable.setText("");
                    break;
                case 2:
                    dataBase.insertTable2(dataBase.nameTable2, dataBase.nameDataBase,
                            4, "Matem", "2018-07-20", 202);
                    informationBlock.setText("Данные были добавлены:\n" + dataBase.selectFromTable2(dataBase.nameDataBase, dataBase.nameTable2));
                    inputTable.setText("");
                    break;
                case 3:
                    dataBase.insertTable3(dataBase.nameTable3, dataBase.nameDataBase, 606, "Ivan",
                            "Smirnov", "Physics", 2);
                    informationBlock.setText("Данные были добавлены:\n" + dataBase.selectFromTable3(dataBase.nameDataBase, dataBase.nameTable3));
                    inputTable.setText("");
                    break;
                case 4:
                    dataBase.insertTable4(dataBase.nameTable4, dataBase.nameDataBase, 3, 150);
                    informationBlock.setText("Данные были добавлены:\n" + dataBase.selectFromTable4(dataBase.nameDataBase, dataBase.nameTable4));
                    inputTable.setText("");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Bad in insert");
        }

    }

    //Метод вызывает метод базы данных для вывода данных из всех таблиц
    private void answerSelectAll() {
        try {
            String out = dataBase.selectFromTable1(dataBase.nameDataBase, dataBase.nameTable1) +
                    "\n\n" + dataBase.selectFromTable2(dataBase.nameDataBase, dataBase.nameTable2) +
                    "\n\n" + dataBase.selectFromTable3(dataBase.nameDataBase, dataBase.nameTable3) +
                    "\n\n" + dataBase.selectFromTable4(dataBase.nameDataBase, dataBase.nameTable4);
            informationBlock.setText(out);
        } catch (SQLException e) {
            System.out.println("Bad in select");
        }

    }

    //Метод вызывает метод базы данных для создания базы данных, записи функций в БД, создания таблиц и заполнения их данными
    private void answerStart() {
        try {
            //Создаем БД
            dataBase = new DataBase();
            dataBase.createDb(dataBase.MAIN_DB_NAME);

            //Вносим функции
            dataBase.fillUpFunctions();

            //Создаем таблицы и заполянем их начальными значениями
            dataBase.createTable4("locations", dataBase.nameDataBase);
            dataBase.createTable3("lecturer", dataBase.nameDataBase);
            dataBase.createTable2("lections", dataBase.nameDataBase);
            dataBase.createTable1("listeners", dataBase.nameDataBase);
            dataBase.fillUpData();

        } catch (SQLException e) {
            System.out.println("Bad in creating");
        }
    }

    //Метод вызывает метод базы данных для удаления БД
    private void answerDropDB() {
        try {
            dataBase.deleteDb(dataBase.nameDataBase);
        } catch (SQLException e) {
            System.out.println("Bad in drop");
        }
    }

    //Главное меню супер интерфейса
    private void whatNext() {
        inputBlock.setText("");
        typeOfRequiredTable = 0;
        informationBlock.setText("1. SelectAll - отобразить содержимое всех таблиц" +
                "\n2. ClearAll - отчистить содержимое всех таблиц" +
                "\n3. Clear - перейти к выбору таблицы для отчистки ее содержимого" +
                "\n4. Insert - перейти к выборы таблицы для вставки нового значения" +
                "\n5. Search - перейти к выбору таблицы для поиска в ней" +
                "\n6. Delete - перейти к удалению поля таблицы" +
                "\n7. Update - обновить данные в таблицу" +
                "\n8. DropDB - удалить базу данных и выйти");
    }

    //Просто запуск
    public static void main(String[] args) {
        Application.launch(args);
    }
}
