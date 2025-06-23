package work;
import java.util.Scanner;
import java.util.Random;

public class MainWork {
	public static void main(String[] args)
	{
		int choice;
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Создание массива:");
		WorkWithArray arrayEx = new WorkWithArray();
		int[] generatedArray = arrayEx.generateArray();
		
		System.out.println("Созданный массив: ");
		WorkWithArray.printArray(generatedArray);
		
		System.out.println();
		System.out.println("2. Выберите метод поиска ключа по значению (если ключ -1 - значение не найдено): ");
		System.out.println("1) линейный поиск;");
		System.out.println("2) бинарный поиск.");
		choice = scanner.nextInt();
		
		switch(choice)
		{
		case 1:
		System.out.println("Ключ (номер в массиве) выбранного вами значения - " + arrayEx.linearSearch(generatedArray));
		break;
		
		case 2:
			System.out.println("Ключ (номер в массиве) выбранного вами значения - "  + arrayEx.binarySearch(generatedArray));
			break;
		default:
			System.out.println("Ошибка ввода.");
		}
		
		
			
		
		
		
	}
}

class WorkWithArray
{
	static void printArray(int[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
	}
	
	int[] manualInput(int[] array)
	{
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] = scanner.nextInt();
		}
		return array;
	}
	
	int[] randomInput(int[] array)
	{
		Random rand = new Random();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите нижнюю границу для рандомной генерации: ");
		int min = scanner.nextInt();
		System.out.println("Введите верхнюю границу для рандомной генерации: ");
		int max = scanner.nextInt();
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] = rand.nextInt(min, max);
		}
		
		return array;
	}
	
	int[] choiceSort(int[] array)
	{
		for(int i = 0; i < array.length - 1; i++)
		{
			int minIndex = i;
			
			for(int j = i + 1; j < array.length; j++)
			{
				if(array[j] < array[minIndex]) minIndex = j;
			}
			
			if(minIndex != i)
			{
				int temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
		
		System.out.println("Отсортированный массив: ");
		printArray(array);
		return array;
	}
	
	public int[] generateArray()
	{
		int size;
		Random rand = new Random();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Введите размер массива: ");
		size = scanner.nextInt();
		
		int[] array = new int[size];
		
		System.out.println("Выберити способ заполнения массива: (1 - вручную; 2 - рандомными числами):");
		int choice = scanner.nextInt();
		
		switch(choice)
		{
		case 1:
			manualInput(array);
			break;
			
		case 2:
			randomInput(array);
			break;
			
		default:
			System.out.println("Вы ввели некорректное число, попробуйте заново");
			break;
		}
		
		return array;
	}
	
	 int linearSearch(int[] array)
	{
		Scanner scanner = new Scanner(System.in);
		int[] sortArray = choiceSort(array);
		System.out.println();
		System.out.println("Выберите значение элемента, который хотите найти: ");
		int value = scanner.nextInt();
		int key;
		
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == value) 
				{
				key = i;
				return key;
				}
			
		}
		
		return -1;
	}
	
	int binarySearch(int[] array)
	{
		Scanner scanner = new Scanner(System.in);
		int[] sortArray = choiceSort(array);
		System.out.println();
		System.out.println("Выберите значение элемента, который хотите найти: ");
		int value = scanner.nextInt();
		int key;
		
		int low = 0;
		int high = array.length - 1;
		
		while(low <= high)
		{
			int mid = (low + high) / 2;
			
			if(array[mid] == value)
			{
				return mid;
			}
			else if(array[mid] < value)
			{
				low = mid + 1;
			}
			else if(array[mid] > value)
			{
				high = mid - 1;
			}
		}
		return -1;
	}
}
