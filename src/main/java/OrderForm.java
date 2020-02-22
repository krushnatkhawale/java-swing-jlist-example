import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.Stream;

public class OrderForm extends JFrame {

    private KeyListener searchOnKeyListener;
    private JLabel hotelLogoLabel;
    private JLabel orderStatusLabel;
    private JPanel topBarPanel, centerPanel;

    private JTextField searchTextArea;
    private JList<String> foodItemsList;
    private final String[] items = {"Aloo gobi", "Aloo tikki", "Aloo matar", "Aloo methi", "Aloo shimla mirch", "Amriti with rabdi", "Amritsari fish", "Amritsari kulcha", "Baati", "Bhatura", "Bhindi masala", "Biryani", "Butter chicken", "Chaat", "Chana masala", "Chapati", "Chicken razala", "Chicken Tikka", "Chicken Tikka masala", "Chole bhature", "Daal baati churma", "Daal puri", "Dal makhani (kali dal)", "Dal fara", "Dal", "Dal fry with tadka", "Dum aloo", "Poha", "Fara", "French bean aloo", "Gajar ka halwa", "Gajar matar aloo", "Gobhi matar", "Imarti", "Hari mutter ka nimona (green peas daal)", "Jalebi", "Jaleba", "Kachori", "Kadai paneer", "Kadhi pakoda", "Karela bharta", "Katha meetha petha / halwakadoo", "Kheer", "Khichdi", "Kofta", "Kulfi falooda", "Lauki ke kofte", "Lauki ki subji", "Litti chokha", "Makki di roti, sarson da saag", "Mathura ke pede", "Methi saag, chaulai saab", "Misi roti", "Mixed vegetable", "Moong dal ka halwa", "Murgh musallam", "Mushroom do pyaza", "Mushroom matar", "Naan Roti", "Navrattan korma", "Pakhala", "Palak paneer", "Paneer butter masala", "Paneer tikka masala", "Pani puri", "Panjeeri", "Papad", "Paratha", "Pattor", "Phirni", "Pindi chana", "Pinni", "Rajma chaval", "Rajma", "Ramatori subji", "Rongi", "Samosa", "Samose", "Sattu ki roti", "Shahi paneer", "Shahi tukra", "Singhada halwa", "Sooji halwa", "Sweet pethas / kesar petha / pista petha", "Vegetable jalfrezi", "Tandoori Chicken", "Tandoori Fish Tikka"};

    public OrderForm() {
        super("Order Form");
        setLayout(new BorderLayout());
        setSize(400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        hotelLogoLabel = new JLabel("My Fav Hotel");
        hotelLogoLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        orderStatusLabel = new JLabel("Order: 0");
        orderStatusLabel.setFont(new Font("Serif", Font.PLAIN, 24));

        topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());
        topBarPanel.add(hotelLogoLabel, BorderLayout.WEST);
        topBarPanel.add(orderStatusLabel, BorderLayout.EAST);

        searchTextArea = new JTextField(50);
        foodItemsList = new JList<>(items);

        searchOnKeyListener = new SearchKeyListener(foodItemsList, searchTextArea, items);
        searchTextArea.addKeyListener(searchOnKeyListener);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(searchTextArea, BorderLayout.NORTH);
        centerPanel.add(foodItemsList, BorderLayout.CENTER);
        centerPanel.add(new JScrollPane(foodItemsList));

        add(topBarPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    class SearchKeyListener implements KeyListener {

        private JList<String> eventList;
        private String[] fullItemList;
        private String[] filteredList;
        private JTextField searchTextArea;

        public SearchKeyListener(JList<String> eventList, JTextField searchTextArea, String[] fullItemList) {
            this.eventList = eventList;
            this.searchTextArea = searchTextArea;
            this.fullItemList = fullItemList;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            String searchText = searchTextArea.getText();
            filteredList = Stream.of(fullItemList)
                    .map(String::toLowerCase)
                    .filter(foodItem -> foodItem.contains(searchText.toLowerCase()))
                    .toArray(String[]::new);
            eventList.setListData(filteredList);

        }
    }
}