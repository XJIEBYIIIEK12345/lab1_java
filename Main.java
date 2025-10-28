package laba1;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        
        // Добавление элементов
        vector.add(10);
        vector.add(20);
        vector.add(30);
        
        System.out.println("Вектор: " + vector);
        System.out.println("Размер: " + vector.size());
        
        // Добавление по индексу
        vector.add(1, 40);
        System.out.println("После добавления: " + vector);
        
        // Получение элемента
        System.out.println("Элемент по индексу 2: " + vector.get(2));
        
        // Удаление
        vector.remove(0);
        System.out.println("После удаления: " + vector);
        
        // Итерация
        System.out.println("Итерация:");
        for (double lang : vector) {
            System.out.println("- " + lang);
        }
        
        // Проверка наличия
        System.out.println("Содержит 10: " + vector.contains(10));
        System.out.println("Индекс 30: " + vector.indexOf(30));
    }
}

