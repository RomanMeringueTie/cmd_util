# Утилита фильтрации содержимого файлов
При запуске утилиты в командной строке подается несколько файлов, содержащих в
перемешку целые числа, строки и вещественные числа. Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий.

## Описание
На вход программе подаются имена файлов, содержимое которых будет отсортировано.

Входные файлы должны лежать в папке "src/main/resources".


Выходные файлы по умолчанию располагаются там же и называются integers.txt, floats.txt, strings.txt.

Выходные файлы создаются по надобности, по умолчанию при каждом запуске программы - перезыписываются.

Ключ -o задаёт пользовательскую директорию выходных файлов.

Ключ -p задаёт префикс имён выходных файлов.

Ключ -s позволяет вывести базовую статистику работы программы.

Ключ -f позволяет вывести расширенную статистику работы программы.

Ключ -a позволяет не перезаписывать выходные файлы, а дополнять их.

## Технологии
- Java (версия 21.0.1)
- Gradle (версия 8.5)
# Пример использования
```sh
cd out\artifacts\CFT_main_jar
java -jar CFT.main.jar <arguments>
```
Аргументы могут прописываться в любом порядке.