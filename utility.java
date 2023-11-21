
// получение хеша объекта
Integer.toHexString(hashCode());

// вставка текста по шаблону
String.format("%s, hello!", "Vasya");

// сравнение строки через equals
if ("Sometext".equals(param)) {}

// узнать доступное кол-во процессоров
// можно сделать многопоточку, которая нагрузит все ресурсы равномерно
int a = Runtime.getRuntime().availableProcessors();
