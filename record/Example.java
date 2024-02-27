// компилятор преобразует record в final class 
// автоматически генерируются методы 
// toString(), hashCode() и equals()
// и параметризованный конструктор 
// геттеры не имеют приставки get, называются как параметры 

public record Example<T, U>(T first, U second) {}

// пример использования 
Example<String, Integer> a = new Example<>( "Hi!" , 123);

String name = a.first();
int age = a.second();
