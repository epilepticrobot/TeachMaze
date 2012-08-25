/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.viewer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import maze.Direction;
import maze.MazeConstants;
import maze.builder.RandomMazeBuilder;
import maze.pieces.EmptySpace;
import maze.pieces.MazePiece;
import maze.pieces.UndefinedMazePiece;
import maze.pieces.Wall;

/**
 *
 * @author Rob.Erwin@gmail.com
 */
public class MazeJPanel2 extends javax.swing.JPanel {

    private Set<MazePiece> drawnPieces = new HashSet<>();
    private MazePiece startingPiece;
    /**
     * Creates new form MazeJPanel2
     */
    public MazeJPanel2() {
        initComponents();
        RandomMazeBuilder rmb = new RandomMazeBuilder();
        startingPiece = rmb.buildRandomMaze();
    }

    private void drawMaze(MazePiece currentPiece, double x, double y, Graphics2D g2) {
        //draw the current piece
        if (!(currentPiece instanceof UndefinedMazePiece)) {
            Rectangle r = new CenteredRectangle((int) x, (int) y, (int) currentPiece.getWidth(), (int) currentPiece.getHeight());
            g2.setPaint(currentPiece.getColor());
            //Random rand = new Random();
            //g2.setPaint(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            g2.fill(r);
            drawnPieces.add(currentPiece);
        }

        //draw all empty spaces first
        MazePiece adjacentPiece;
        for (Direction direction : MazeConstants.DIRECTIONS) {
            adjacentPiece = currentPiece.getDirection(direction);
            if (adjacentPiece instanceof EmptySpace && !drawnPieces.contains(adjacentPiece)) {
                // works here because all pieces are same size
                drawMaze(adjacentPiece,
                        x + direction.getXdiff() * currentPiece.getWidth() / 2.0 + direction.getXdiff() * adjacentPiece.getWidth() / 2.0,
                        y + direction.getYdiff() * currentPiece.getHeight() / 2.0 + direction.getYdiff() * adjacentPiece.getHeight() / 2.0,
                        g2);
            }
        }
        //then draw all walls
        for (Direction direction : MazeConstants.DIRECTIONS) {
            adjacentPiece = currentPiece.getDirection(direction);
            if (adjacentPiece instanceof Wall && !drawnPieces.contains(adjacentPiece)) {
// THIS CODE WAS FROM BEFORE USING CenteredRectangle
//                if (direction == MazeConstants.NORTH) {
//                    drawMaze(adjacentPiece, x + direction.getXdiff() * currentPiece.getWidth(), y + direction.getYdiff() * currentPiece.getHeight() * .5, g2);
//                } else if (direction == MazeConstants.EAST) {
//                    drawMaze(adjacentPiece, x + direction.getXdiff() * currentPiece.getWidth() - .5 * adjacentPiece.getWidth(), y + direction.getYdiff() * currentPiece.getHeight(), g2);
//                } else if (direction == MazeConstants.WEST) {
//                    drawMaze(adjacentPiece, x + direction.getXdiff() * adjacentPiece.getWidth() * .5, y + direction.getYdiff() * adjacentPiece.getHeight(), g2);
//                } else {
//                    drawMaze(adjacentPiece, x + direction.getXdiff() * adjacentPiece.getWidth(), y + direction.getYdiff() * adjacentPiece.getHeight() * .5, g2);
//                }
                drawMaze(adjacentPiece,
                        x + direction.getXdiff() * currentPiece.getWidth() / 2.0,
                        y + direction.getYdiff() * currentPiece.getHeight() / 2.0,
                        g2);

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 255, 204));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        drawnPieces.clear();
        drawMaze(startingPiece, getWidth()/2.0, getHeight()/2.0, (Graphics2D)this.getParent().getGraphics()/*JFrame graphics*/);
    }//GEN-LAST:event_formComponentShown

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        drawnPieces.clear();
        drawMaze(startingPiece, getWidth()/2.0, getHeight()/2.0, (Graphics2D)this.getParent().getGraphics()/*JFrame graphics*/);
    }//GEN-LAST:event_formMouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        drawnPieces.clear();
        drawMaze(startingPiece, getWidth()/2.0, getHeight()/2.0, (Graphics2D)this.getParent().getGraphics()/*JFrame graphics*/);
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
