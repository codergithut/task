import com.factory.task.HuffmanTree;
import org.junit.jupiter.api.Test;

/**
 * Created by tianjian on 2020/1/15.
 */
public class TestHuffmanTree {

    @Test
    public void testHuffmanTree() {
        HuffmanTree huffmanTree = new HuffmanTree();
        int[] w = {5, 29, 7, 8, 14, 23, 3, 11};
        String[] elem = {"A", "B", "C", "D", "E", "F", "G", "H"};
        huffmanTree.createHuffmanTree(w, elem).printHuffmanTree();
        huffmanTree.createHuffmanCode(w, elem).printHuffmanCode();
    }
}
