package timedpatience.ui;

/**
 * Creates a game of Perpetual Motion. User wins when there are
 * no cards left in any of the piles.
 * 
 * @author Cloette Owensby, Connie Uribe, Bolun Zhang
 * @date 11/16/2014
 */

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import timedpatience.model.Card;
import timedpatience.model.Deck;

public class BoardDriver
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Perpetual Motion");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(400, 300);
      
      // Creates the stock pile.
      
      final Deck deckA = new Deck();
      deckA.fill();
      deckA.shuffle();
      
      // Creates the other four piles
      
      final Deck deckB = new Deck();
      final Deck deckC = new Deck();
      final Deck deckD = new Deck();
      final Deck deckE = new Deck();
      
      // Loads the card images
      
      File imageDirectory = new File("src/main/Resources/Cards");
      CardImages images = new CardImages(imageDirectory);
      
      final DeckComponent dcA = new DeckComponent(deckA, images);
      final DeckComponent dcB = new DeckComponent(deckB, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcC = new DeckComponent(deckC, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcD = new DeckComponent(deckD, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcE = new DeckComponent(deckE, images, DeckComponent.FAN_VERTICAL);
      
      dcA.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            // When clicked, deal a card to deck B, C, D, and E.
            
            Card newTop = deckComponent.getTopCard(); //top of the stock pile
            
            if (newTop == null){
               // If all piles are empty, the user has won. Print a winner message.
               if (deckA.isEmpty() && deckB.isEmpty() && deckC.isEmpty() && deckD.isEmpty()){
                  JOptionPane.showMessageDialog(null, "Congrats! You won. Play again?");
                  deckA.fill();
                  deckA.shuffle();
               }
               // all the cards go back into the stock pile
               else{
                  // empties out the pile back into the stock
                  for (int i=deckB.size(); i>0; i--){
                     Card card1 = dcB.getTopCard();
                     card1.flip();
                     dcB.removeTopCard();
                     deckA.add(card1);
                     
                  }
                  // empties out the pile back into the stock
                  for (int i=deckC.size(); i>0; i--){
                     Card card2 = dcC.getTopCard();
                     card2.flip();
                     dcC.removeTopCard();
                     deckA.add(card2);
                  }
                  // empties out the pile back into the stock
                  for (int i=deckD.size(); i>0; i--){
                     Card card3 = dcD.getTopCard();
                     card3.flip();
                     dcD.removeTopCard();
                     deckA.add(card3);
                  }
                  // empties out the pile back into the stock
                  for (int i=deckE.size(); i>0; i--){
                     Card card4 = dcE.getTopCard();
                     card4.flip();
                     dcE.removeTopCard();
                     deckA.add(card4);
                  }
               }
            }
               
            if (newTop != null && !newTop.isFaceUp()) {
               //deckComponent.flipTopCard();
            }
            
            Card card1 = deckComponent.removeTopCard();
            Card card2 = deckComponent.removeTopCard();
            Card card3 = deckComponent.removeTopCard();
            Card card4 = deckComponent.removeTopCard();
            
            // as long as there are enough cards left to deal, deal them upon click
            
            if (card1 != null && card2 != null && card3 != null && card4 != null)
            {
               dcB.addCard(card1);
               dcB.flipTopCard();
               dcC.addCard(card2);
               dcC.flipTopCard();
               dcD.addCard(card3);
               dcD.flipTopCard();
               dcE.addCard(card4);
               dcE.flipTopCard();
               
               // if all dealt cards have the same rank, remove those 4 from the board
               
               if (card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank() && card3.getRank() == card4.getRank())
               {
                 dcB.removeTopCard();
                 dcC.removeTopCard();
                 dcD.removeTopCard();
                 dcE.removeTopCard();
                 JOptionPane.showMessageDialog(null, "All cards had the same rank and were automatically removed.");
               }
                             
            }
         }
         
         // Dragging the card
         
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Never allows drops.
            return false;
         }
      });
      
      dcB.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank() == deckComponent.getPrevCard().getRank()){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      dcC.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank() == deckComponent.getPrevCard().getRank()){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      dcD.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank() == deckComponent.getPrevCard().getRank()){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      dcE.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank() == deckComponent.getPrevCard().getRank()){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      
      // allows the cards to be dragged onto other DeckComponents
      
      dcA.setDraggable(true);
      dcB.setDraggable(true);
      dcC.setDraggable(true);
      dcD.setDraggable(true);
      dcE.setDraggable(true);
      
      // Creates the board and the 5 piles.
            
      JPanel panel = new JPanel();
      panel.add(dcA);
      panel.add(dcB);
      panel.add(dcC);
      panel.add(dcD);
      panel.add(dcE);
      
      frame.add(panel);
      frame.setVisible(true);
   }
}
