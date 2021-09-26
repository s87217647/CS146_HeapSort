import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
      //TODO: Give them indexes
      for(int i = 0; i < students.size(); i++){
         students.get(i).setIndex(i);
      }
   }
      
   public MaxHeap(Collection<Student> collection)
   {
      students = new ArrayList<Student>(collection);
      for(int i = size()/2 - 1; i >= 0; i--)
      {
         maxHeapify(i);
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0,students.get(size()-1));
      students.get(0).setIndex(0);// Changes
      students.remove(size()-1);
      maxHeapify(0); // Heapify definitely change the order of elements, need to change index
      return value;
   }
    
   public int size()
   {
      return students.size();
   }
   


   public void insert(Student elt)
   {
      //Please write me.  I should add the given student into the heap,
      //following the insert algorithm from the videos.
      students.add(elt); // Keep swapping with the parent while it's larger
      elt.setIndex(students.size() - 1 ); // changes
      bubbleUp(students.size() - 1);

   }
   
   public void addGrade(Student elt, double gradePointsPerUnit, int units)
   {
      //Please write me.  I should change the student's gpa (using a method
	  //from the student class), and then adjust the heap as needed using
	  //the changeKey algorithm from the videos.
      int studentIndex = elt.getIndex();
      elt.addGrade(gradePointsPerUnit,units);
      bubbleUp(studentIndex);
      maxHeapify(studentIndex);
   }

   private void bubbleUp(int childIndex){
      int parentIndex = parent(childIndex);
      while (students.get(childIndex).compareTo(students.get(parentIndex)) > 0){
         swap(childIndex,parentIndex);
         childIndex = parentIndex;
         parentIndex = parent(childIndex);
      }

   }

   private int parent(int index)
   {
      return (index - 1)/2;
   }
   
   private int left(int index)
   {
      return 2 * index + 1;
   }
   
   private int right(int index)
   {
      return 2 * index + 2;
   }
   
   private void swap(int from, int to) // Key of the keys, it has to be correct
   {
      // I hope this swap changes index as well
      Student val = students.get(from); // index will be changed, but it shouldn't
      students.set(from,  students.get(to));
      students.set(to,  val);

      students.get(from).setIndex(from);// Changes
      students.get(to).setIndex(to); // Changes
   }
   
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
         maxHeapify(largest);
      }  
   }   
}