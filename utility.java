
// получение хеша объекта
Integer.toHexString(hashCode());

// вставка текста по шаблону
String.format("%s, hello!", "Vasya");

// сравнение строки через equals
if ("Sometext".equals(param)) {}

// узнать доступное кол-во процессоров
// можно сделать многопоточку, которая нагрузит все ресурсы равномерно
int a = Runtime.getRuntime().availableProcessors();

// вывести переменные окружения построчно
Map<String, String> envs = System.getenv();
for (Map.Entry<String, String> env: envs.entrySet()) {
    System.out.println(env.getKey() + " - " + env.getValue());
}
