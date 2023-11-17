import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

import org.w3c.dom.Node;





public class TP02 {
    private static InputReader in;
    private static PrintWriter out;
    private static LinkedListKelas list_kelas = new LinkedListKelas();
    private static ArrayList<Integer> temp = new ArrayList<>();



    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        int M = in.nextInt(); //Banyak kelas
        int Mi;  // Mi  Banyak siswa pada kelas i
        
        int id=0;
        for (int i=0; i < M; i++){
            KelasAVLtree kelas = new KelasAVLtree(i+1); // bikin kelas baru
            list_kelas.insert_kelas(kelas);
            Mi = in.nextInt();
            temp.add(Mi);
        }
        id=0;
        KelasAVLtree kelas = list_kelas.head;
        for(int i=0;i < M; i++){
            for (int j =0; j < temp.get(i);j++){
                kelas.insertId(++id, in.nextInt());
            }
            kelas = kelas.next;
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
        KelasAVLtree kelas  = list_kelas.pakcil;
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
        KelasAVLtree kelas  = list_kelas.pakcil;
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
        
        if (list_kelas.size()<3){// kalo cuma 2 kelas
            if (list_kelas.pakcil == list_kelas.head){// kelas pakcil = terburuk
                KelasAVLtree  M = list_kelas.pakcil;
                KelasAVLtree  MA = list_kelas.pakcil.prev;
                Siswa[] siswa_dari_M = new Siswa[3];
                Siswa[] siswa_dari_MA = new Siswa[3];
                for(int i =0;i<3;i++){
                    Siswa siswaM = ;//siswa terbaik
                    Siswa siswaMA = ;//siswa terburuk
                    siswa_dari_M[i] = siswaM;
                    siswa_dari_MA[i] = siswaMA;
                }
                for(int i =0;i<3;i++){
                    M.insertSiswa(siswa_dari_MA[i]);
                    MA.insertSiswa(siswa_dari_M[i]);
                }
            }

        }
        else{
            KelasAVLtree  M = list_kelas.pakcil;
            KelasAVLtree  MA = list_kelas.pakcil.prev;
            KelasAVLtree  MB = list_kelas.pakcil.next;



        }
        
        
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



    

    static class LinkedListKelas {
        public KelasAVLtree pakcil;
        public KelasAVLtree head,tail;
        
        
        public LinkedListKelas(){
            head=null;
            tail=null;
        }

        // insert kelas di ujung list
        public void insert_kelas(KelasAVLtree kelas){
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
                KelasAVLtree current = head;

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

        public int size(){
            int count = 0;
            KelasAVLtree current = this.head;
            while (current.next != null) {
                current = current.next;
                count++;
            }
            return count;
        }
    }

    static class Siswa  {      
    int id;  //id siswa
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
        id += 1;  
        h = 0;  
    }  
    // parameterized constructor  
    public Siswa(int id)  
    {  
        leftChild = null;  
        rightChild = null;  
        this.id = id;  
        h = 0;  
    }       


}  
  
// create class KelasAVLtree for constructing AVL Tree  
static class KelasAVLtree  // Kelas
{  
    private Siswa rootNode;  
    public int id;     
    KelasAVLtree next,prev;
    public int rata2;

    //Constructor to set null value to the rootNode  
    public KelasAVLtree(int id)  
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
      
    // create insertId() to insert id to to the AVL Tree  
    public void insertId(int id, int poin)  
    {  
        rootNode = insertId(id, poin,rootNode);  
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
      
      
    //create insertId() method to insert data in the AVL Tree recursively   
    private Siswa insertId(int id,int poin, Siswa node)
    {  
        //check whether the node is null or not  
        if (node == null)  
            node = new Siswa(id);  
        //insert a node in case when the given id is lesser than the id of the root node  
        else if (poin < node.poin)  
        {  
            node.leftChild = insertId( id,poin, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )  
                if( poin < node.leftChild.poin )  
                    node = rotateWithLeftChild( node );  
                else  
                    node = doubleWithLeftChild( node );  
        }  
        else if( poin > node.poin )  
        {  
            node.rightChild = insertId( id,poin ,node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )  
                if( poin > node.rightChild.poin)  
                    node = rotateWithRightChild( node );  
                else  
                    node = doubleWithRightChild( node );  
        }  
        else if(poin == node.poin && id < node.id) {

            node.leftChild = insertId( id,poin, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )  
                if( id < node.leftChild.id )  
                    node = rotateWithLeftChild( node );  
                else  
                    node = doubleWithLeftChild( node );
        }  
        else if (poin == node.poin && id > node.id){
            node.rightChild = insertId( id,poin ,node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )  
                if( id > node.rightChild.id)  
                    node = rotateWithRightChild( node );  
                else  
                    node = doubleWithRightChild( node ); 
        }
        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1;  
          
        return node;  
          
    }  

    private Siswa insertSiswa(Siswa siswa, Siswa node)  
    {  
        //check whether the node is null or not  
        if (node == null)  
            node = new Siswa(siswa.id);  
        //insert a node in case when the given id is lesser than the id of the root node  
        else if (siswa.id < node.id)  
        {  
            node.leftChild = insertSiswa( siswa.id, node.leftChild );  
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )  
                if( siswa.id < node.leftChild.id )  
                    node = rotateWithLeftChild( node );  
                else  
                    node = doubleWithLeftChild( node );  
        }  
        else if( siswa.id > node.id )  
        {  
            node.rightChild = insertId( siswa.id, node.rightChild );  
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )  
                if( siswa.id > node.rightChild.id)  
                    node = rotateWithRightChild( node );  
                else  
                    node = doubleWithRightChild( node );  
        }  
        else  
            ;  // if the id is already present in the tree, we will do nothing   
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
  
    //create searchid() method to find an id in the AVL Tree  
    public boolean searchid(int id)  
    {  
        return searchid(rootNode, id);  
    }  
  
    private boolean searchid(Siswa head, int id)  
    {  
        boolean check = false;  
        while ((head != null) && !check)  
        {  
            int headid = head.id;  
            if (id < headid)  
                head = head.leftChild;  
            else if (id > headid)  
                head = head.rightChild;  
            else  
            {  
                check = true;  
                break;  
            }  
            check = searchid(head, id);  
        }  
        return check;  
    }  

    // Public method to search for an id in the AVL tree
    public Siswa search(int id) {
        return search(rootNode, id);
    }

    // Private method to search for an id in the AVL tree
    private Siswa search(Siswa node, int id) {
        if (node == null || node.id == id) {
            return node;
        }

        if (id < node.id) {
            return search(node.leftChild, id);
        } else {
            return search(node.rightChild, id);
        }
    }


    int getBalance(Siswa N) 
    { 
        if (N == null) 
            return 0; 
        return getHeight(N.leftChild) - getHeight(N.rightChild); 
    } 

    Siswa minValueNode(Siswa node) 
    { 
        Siswa current = node; 
 
        /* loop down to find the leftmost leaf */
        while (current.leftChild != null) 
        current = current.leftChild; 
 
        return current; 
    }


    Siswa deleteSiswa(Siswa root, int id) 
    { 
        // STEP 1: PERFORM STANDARD BST DELETE 
        if (root == null) 
            return root; 
 
        // If the id to be deleted is smaller than 
        // the root's id, then it lies in left subtree 
        if (id < root.id) 
            root.leftChild = deleteSiswa(root.leftChild, id); 
 
        // If the id to be deleted is greater than the 
        // root's id, then it lies in right subtree 
        else if (id > root.id) 
            root.rightChild = deleteSiswa(root.rightChild, id); 
 
        // if id is same as root's id, then this is the node 
        // to be deleted 
        else
        { 
 
            // node with only one child or no child 
            if ((root.leftChild == null) || (root.rightChild == null)) 
            { 
                Siswa temp = null; 
                if (temp == root.leftChild) 
                    temp = root.rightChild; 
                else
                    temp = root.leftChild; 
 
                // No child case 
                if (temp == null) 
                { 
                    temp = root; 
                    root = null; 
                } 
                else // One child case 
                    root = temp; // Copy the contents of 
                                // the non-empty child 
            } 
            else
            { 
 
                // node with two children: Get the inorder 
                // successor (smallest in the right subtree) 
                Siswa temp = minValueNode(root.rightChild); 
 
                // Copy the inorder successor's data to this node 
                root.id = temp.id; 
 
                // Delete the inorder successor 
                root.rightChild = deleteSiswa(root.rightChild, temp.id); 
            } 
        } 
 
        // If the tree had only one node then return 
        if (root == null) 
            return root; 
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE 
        root.h = getMaxHeight(getHeight(root.leftChild), getHeight(root.rightChild)) + 1; 
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether 
        // this node became unbalanced) 
        int balance = getBalance(root); 
 
        // If this node becomes unbalanced, then there are 4 cases 
        // Left Left Case 
        if (balance > 1 && getBalance(root.leftChild) >= 0) 
            return rotateWithLeftChild(root); 
 
        // Left Right Case 
        if (balance > 1 && getBalance(root.leftChild) < 0) 
        { 
            root.leftChild = rotateWithRightChild(root.leftChild); 
            return rotateWithLeftChild(root); 
        } 
 
        // Right Right Case 
        if (balance < -1 && getBalance(root.rightChild) <= 0) 
            return rotateWithRightChild(root); 
 
        // Right Left Case 
        if (balance < -1 && getBalance(root.rightChild) > 0) 
        { 
            root.rightChild = rotateWithLeftChild(root.rightChild); 
            return rotateWithRightChild(root); 
        } 
 
        return root; 
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
//https://github.com/eugeniusms/SDA-2022/blob/master/Lab05%20-%20Kejuaraan%20Sofita/Lab05Stack.java
//https://www.geeksforgeeks.org/deletion-in-an-avl-tree/