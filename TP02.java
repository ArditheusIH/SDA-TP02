import java.io.*;
import java.util.*;

import org.w3c.dom.Node;





public class TP02 {
    private static InputReader in;
    private static PrintWriter out;
    private static LinkedListKelas list_kelas = new LinkedListKelas();



    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        int M = in.nextInt();
        int N;  // Mi
        int O=0; // sigma N
        int id=0;
        for (int i=0; i < M; i++){
            ConstructAVLTree kelas = new ConstructAVLTree(i+1); // bikin kelas baru
            
            N = in.nextInt();
            O += N;
            for(int j=0; j < N; j++){
                kelas.rootNode = kelas.insertElement(++id, kelas.rootNode);
            }
        }
        for (int i=0; i < O; i++){
            Siswa siswa = ;
        }

        String Q = in.next();
        if(Q.equals("T")){
            T(in.nextInt(), in.nextInt());
        }
        else if(Q.equals("C")){
            C(in.nextInt());
        }
        else if(Q.equals("G")){
            G(in.next());
        }
        else if(Q.equals("S")){
            S();
        }
        else if(Q.equals("K")){
            K();
        }
        else if(Q.equals("A")){
            A(in.nextInt());
        }
    }

    public static void T(int poin, int id_siswa){
        //TODO:
        //output:Cetak poin siswa setelah ditambah bobot poin tugas
        //Jika siswa tidak ditemukan, maka cetak -1
        ConstructAVLTree kelas  = list_kelas.pakcil;
        Siswa siswa = kelas.search(id_siswa);
        siswa.poin += poin;
        System.out.println(siswa.poin);
    }

    public static void C(int id_siswa){
        //TODO:
        //Jika siswa ditemukan dan merupakan curang pertama, maka cetak 0.
        // ○ Jika siswa ditemukan dan merupakan curang kedua, maka siswa akan diturunkan ke kelas
        // yang lebih buruk. Cetak id dari kelas terbarunya.
        // ○ Jika siswa ditemukan dan merupakan curang ketiga, maka siswa akan di DO. Cetak [id_siswa].
        // ○ Jika siswa tidak ditemukan, maka cetak -1.
        ConstructAVLTree kelas  = list_kelas.pakcil;
        Siswa siswa = kelas.search(id_siswa);
        siswa.banyak_kecurangan += 1;
        siswa.poin = 0;
        if (siswa.banyak_kecurangan == 1)System.out.println(0);
        else if(siswa.banyak_kecurangan == 2){
            //TODO: siswa dipindahkan ke kelas yang paling buruk
        }
        else if(siswa.banyak_kecurangan == 3){
            //TODO: siswa dikick dari sekolah

            //TODO: kalo jmlh siswa di kelas < 6 maka hapus kelas, siswanya pindah ke kelas bawahnya.
            //kalo kelasnya paling buruk pindah ke atasnya
        }
    }

    public static void G(String l_r){
        //TODO:
        //Cetak nomor id kelas tempat Pakcil sekarang berada.
        if (l_r.equals("R")) list_kelas.pakcil = list_kelas.pakcil.next;
        else list_kelas.pakcil = list_kelas.pakcil.prev;
        System.out.println(list_kelas.pakcil.id);
    }

    public static void S(){
        //TODO:
        //Cetak id siswa terbaik dan terburuk pada kelas tempat dilakukan.
        // ○ Jika sekolah hanya memiliki 1 kelas, cetak -1 -1
        
    }

    public static void K(){
        //TODO:
        //Cetak urutan baru kelas tempat Pakcil sekarang berada. (Note: urutan tidak sama dengan
        // nomor id kelas)
        // ○ Urutan kelas dimulai dari 1.
    }

    public static void A(int banyak_siswa){
        //TODO:
        //Cetak id dari kelas yang baru ditambahkan ke sekolah.
    }

    // static class Siswa extends Node {
    //     int id,poin = 0;
    //     public Siswa(){
            
    //         this.id =+ 1;
    //         this.poin = 0;
    //     }
    // }

    // static class Kelas {
    //     int id = 0;
    //     public Kelas(){
    //         this.id =+ 1;
    //     }
    // }

    

    static class LinkedListKelas {
        public ConstructAVLTree pakcil;
        public ConstructAVLTree head,tail;
        
        
        public LinkedListKelas(){
            head=null;
            tail=null;
        }

        // insert kelas di ujung list
        public void insert_kelas(ConstructAVLTree kelas){
            if (head == null){
                pakcil = kelas;
                head = kelas;
                tail = kelas;
            }
            else{
                tail.next = kelas;
                kelas.prev = tail;
                tail = kelas;
            }
            
        }

        public void bubbleSort() {
            if (head == null || head.next == null) {
                // List is already sorted (or empty)
                return;
            }

            boolean sorted;
            do {
                sorted = true;
                ConstructAVLTree current = head;

                while (current.next != null) {
                    if (current.rata2 > current.next.rata2) {
                        // Swap data in nodes
                        int temp = current.rata2;
                        current.rata2 = current.next.rata2;
                        current.next.rata2 = temp;
                        sorted = false;
                    }

                    current = current.next;
                }
            } while (!sorted);
        }
    }

    static class Siswa  {      
    int element;  //id siswa
    int poin; // poin siswa
    int banyak_kecurangan = 0;
    int h;  //for height  
    Siswa leftChild;  
    Siswa rightChild;  
      
    //default constructor to create null node  
    public Siswa()  
    {  
        leftChild = null;  
        rightChild = null;  
        element += 1;  
        h = 0;  
    }  
    // parameterized constructor  
    public Siswa(int element)  
    {  
        leftChild = null;  
        rightChild = null;  
        this.element = element;  
        h = 0;  
    }       


}  
  
// create class ConstructAVLTree for constructing AVL Tree  
static class ConstructAVLTree  // Kelas
{  
    private Siswa rootNode;  
    public int id;     
    ConstructAVLTree next,prev;
    public int rata2;

    //Constructor to set null value to the rootNode  
    public ConstructAVLTree(int id)  
    {  
        rootNode = null;  
        this.id = id;
    }  


    public Siswa findMax(Siswa node){
        Siswa current = node;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    public void assign_poin(int id, int point){
        Siswa siswa = search(id);
        siswa.poin = point;
    }

    public void hitung_rata2(){
        //TODO:
        
    }
      
    //create removeAll() method to make AVL Tree empty  
    public void removeAll()  
    {  
        rootNode = null;  
    }  
      
    // create checkEmpty() method to check whether the AVL Tree is empty or not  
    public boolean checkEmpty()  
    {  
        if(rootNode == null)  
            return true;  
        else   
            return false;  
    }  
      
    // create insertElement() to insert element to to the AVL Tree  
    public void insertElement(int element)  
    {  
        rootNode = insertElement(element, rootNode);  
    }  
      
    //create getHeight() method to get the height of the AVL Tree  
    private int getHeight(Siswa node )  
    {  
        return node == null ? -1 : node.h;  
    }  
      
    //create maxNode() method to get the maximum height from left and right node  
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)  
    {  
    return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;  
    }  
      
      
    //create insertElement() method to insert data in the AVL Tree recursively   
    private Siswa insertElement(int element, Siswa node)  
    {  
        //check whether the node is null or not  
        if (node == null)  
            node = new Siswa(element);  
        //insert a node in case when the given element is lesser than the element of the root node  
        else if (element < node.element)  
        {  
            node.leftChild = insertElement( element, node.leftChild );  
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )  
                if( element < node.leftChild.element )  
                    node = rotateWithLeftChild( node );  
                else  
                    node = doubleWithLeftChild( node );  
        }  
        else if( element > node.element )  
        {  
            node.rightChild = insertElement( element, node.rightChild );  
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )  
                if( element > node.rightChild.element)  
                    node = rotateWithRightChild( node );  
                else  
                    node = doubleWithRightChild( node );  
        }  
        else  
            ;  // if the element is already present in the tree, we will do nothing   
        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1;  
          
        return node;  
          
    }  
      
    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child        
    private Siswa rotateWithLeftChild(Siswa node2)  
    {  
        Siswa node1 = node2.leftChild;  
        node2.leftChild = node1.rightChild;  
        node1.rightChild = node2;  
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1;  
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;  
        return node1;  
    }  
  
    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child        
    private Siswa rotateWithRightChild(Siswa node1)  
    {  
        Siswa node2 = node1.rightChild;  
        node1.rightChild = node2.leftChild;  
        node2.leftChild = node1;  
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;  
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;  
        return node2;  
    }  
  
    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child  
    private Siswa doubleWithLeftChild(Siswa node3)  
    {  
        node3.leftChild = rotateWithRightChild( node3.leftChild );  
        return rotateWithLeftChild( node3 );  
    }  
  
    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child  
    private Siswa doubleWithRightChild(Siswa node1)  
    {  
        node1.rightChild = rotateWithLeftChild( node1.rightChild );  
        return rotateWithRightChild( node1 );  
    }      
  
    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree  
    public int getTotalNumberOfNodes()  
    {  
        return getTotalNumberOfNodes(rootNode);  
    }  
    private int getTotalNumberOfNodes(Siswa head)  
    {  
        if (head == null)  
            return 0;  
        else  
        {  
            int length = 1;  
            length = length + getTotalNumberOfNodes(head.leftChild);  
            length = length + getTotalNumberOfNodes(head.rightChild);  
            return length;  
        }  
    }  
  
    //create searchElement() method to find an element in the AVL Tree  
    public boolean searchElement(int element)  
    {  
        return searchElement(rootNode, element);  
    }  
  
    private boolean searchElement(Siswa head, int element)  
    {  
        boolean check = false;  
        while ((head != null) && !check)  
        {  
            int headElement = head.element;  
            if (element < headElement)  
                head = head.leftChild;  
            else if (element > headElement)  
                head = head.rightChild;  
            else  
            {  
                check = true;  
                break;  
            }  
            check = searchElement(head, element);  
        }  
        return check;  
    }  

    // Public method to search for an element in the AVL tree
    public Siswa search(int element) {
        return search(rootNode, element);
    }

    // Private method to search for an element in the AVL tree
    private Siswa search(Siswa node, int element) {
        if (node == null || node.element == element) {
            return node;
        }

        if (element < node.element) {
            return search(node.leftChild, element);
        } else {
            return search(node.rightChild, element);
        }
    }

    
}
















static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

}



//references:
//https://www.javatpoint.com/avl-tree-program-in-java