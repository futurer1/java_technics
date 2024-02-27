public record Example<T, U>(T first, U second) {}

// пример использования 
Example<String, Integer> a = new Example<>( "Hi!" , 123);
