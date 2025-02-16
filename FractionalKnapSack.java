import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item> {
    int capacity;
    List<Item> lst;

    double profit;


    FractionalKnapSack(int c, List<Item> lst1) {
        capacity = c;
        lst = lst1;
    }

    public static class Item implements Comparable<Item> {
        double weight, value, ReletiveWeight;

        Item(double w, double v) {
            weight = w;
            value = v;
            ReletiveWeight = v/w;
        }

        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }


        @Override
        public int compareTo(Item other) {
            if (this.ReletiveWeight > other.ReletiveWeight){
                return -1;
            }
            else {
                return 1;
            }
        }

    }


    @Override
    public Iterator<Item> selection() {
        Collections.sort(this.lst);
        return this.lst.iterator();
    }

    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        double totalWeight = sum(candidates_lst);
        if (this.capacity == 0.0){
            return false;
        }
        if (totalWeight+ element.weight <= capacity){
            return true;
        }
        else {
            double fraction = (capacity-totalWeight)/element.weight;
            element.value = element.value * fraction;
            element.weight = element.weight * fraction;
            return true;
        }
    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {
        candidates_lst.add(element);
    }

    @Override
    public boolean solution(List<Item> candidates_lst) {
        return sum(candidates_lst) == capacity | !selection().hasNext();
    }

    private Double sum(List<Item> lst) {
        double sum = 0;
        for (Item element : lst) {
            sum += element.weight;
        }
        return sum;
    }
}
