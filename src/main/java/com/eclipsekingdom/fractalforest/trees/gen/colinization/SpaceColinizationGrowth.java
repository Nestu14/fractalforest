package com.eclipsekingdom.fractalforest.trees.gen.colinization;

import com.eclipsekingdom.fractalforest.trees.gen.LeafCluster;
import com.eclipsekingdom.fractalforest.util.math.shape.IShape;
import com.eclipsekingdom.fractalforest.util.math.shape.RectangularSolid;
import org.bukkit.util.Vector;

import java.util.*;

public class SpaceColinizationGrowth {

    private boolean finished = false;
    private Vector origin = new Vector(0,0,0);
    private int dotCount;
    private int trunkHeight;
    private int treeWidth;
    private int treeHeight;
    private int minDistance;
    private int maxDistance;
    private int segmentLength;

    private Segment root;
    private List<Dot> dots = new ArrayList<>();
    private Map<Vector, Segment> branches = new HashMap<>();

    private IShape crown;

    public SpaceColinizationGrowth(int dotCount, int segmentLength, int trunkHeight, int treeWidth, int treeHeight, int minDistance, int maxDistance){
        this.dotCount = dotCount;
        this.segmentLength = segmentLength;
        this.trunkHeight = trunkHeight;
        this.treeWidth = treeWidth;
        this.treeHeight = treeHeight;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    private void generateTrunk() {
        root = new Segment(null, origin, new Vector(0, 1,0));
        branches.put(root.getPosition(), root);

        Segment current = new Segment(root, new Vector(origin.getX(), origin.getY() + segmentLength, origin.getZ()), new Vector(0, 1,0));
        branches.put(current.getPosition(), current);

        while ((root.getPosition().distance(current.getPosition()) < trunkHeight)){
            Segment trunk = new Segment(current, new Vector(current.getPosition().getX(), current.getPosition().getY() + segmentLength, current.getPosition().getZ()), new Vector(0, 1,0));
            branches.put(trunk.getPosition(), trunk);
            current = trunk;
        }
    }

    public void generateTree() {
        reset();
        generateCrown();
        generateTrunk();
        while(!finished){
            grow();
        }
    }

    private void reset(){
        root = null;
        dots.clear();
        branches.clear();
    }

    private void generateCrown() {
        crown = new RectangularSolid(new Vector(origin.getX() - treeWidth/2d, origin.getY() + trunkHeight, origin.getZ() - treeWidth/2d), new Vector(origin.getX()+treeWidth/2d, origin.getY()+treeHeight+trunkHeight, origin.getZ() + treeWidth/2d));
        for (int i = 0; i < dotCount; i++) {
            Dot dot = new Dot(crown.nextPoint());
            dots.add(dot);
        }
    }

    private void grow() {
        if (finished) return;

        if (dots.size() == 0) {
            finished = true;
            return;
        }

        for (int i = 0; i < dots.size(); i++) {
            boolean dotsRemoved = false;

            dots.get(i).setClosestSegment(null);
            Vector direction;

            for (Segment segment : branches.values()) {
                direction = dots.get(i).getPosition().subtract(segment.getPosition());
                float distance = (float) Math.round(direction.length());
                direction.normalize();
                if (distance <= minDistance) {
                    dots.remove(dots.get(i));
                    i--;
                    dotsRemoved = true;
                    break;
                } else if (distance <= maxDistance) {
                    if (dots.get(i).getClosestSegment() == null) {
                        dots.get(i).setClosestSegment(segment);
                    } else if (dots.get(i).getPosition().distance(dots.get(i).getClosestSegment().getPosition()) > distance) {
                        dots.get(i).setClosestSegment(segment);
                    }
                }
            }

            if (!dotsRemoved) {
                if (dots.get(i).getClosestSegment() != null) {
                    Vector dir = dots.get(i).getPosition().subtract(dots.get(i).getClosestSegment().getPosition());
                    dir.normalize();
                    dots.get(i).getClosestSegment().setGrowDirection(dots.get(i).getClosestSegment().getGrowDirection().add(dir));
                    dots.get(i).getClosestSegment().setGrowCount(dots.get(i).getClosestSegment().getGrowCount() + 1);
                }
            }
        }

        HashSet<Segment> newSegments = new HashSet<>();
        for (Segment segment : branches.values()) {
            if (segment.getGrowCount() > 0) {
                Vector avgDirection = segment.getGrowDirection().multiply(1 / (double) segment.getGrowCount());
                avgDirection.normalize();
                Segment newSegment = new Segment(segment, segment.getPosition().add(avgDirection).multiply(segmentLength), avgDirection);
                newSegments.add(newSegment);
                segment.reset();
            }else{
                if(segment.getPosition().getY() > origin.getY() + trunkHeight){
                    if(!positionToLeaves.containsKey(segment.getEnd(segmentLength))){
                        positionToLeaves.put(segment.getEnd(segmentLength),new LeafCluster(segment.getEnd(segmentLength), 1));
                    }
                }
            }
        }

        boolean branchAdded = false;
        for (Segment segment : newSegments) {
            Segment existing = branches.get(segment.getPosition());
            if (existing == null) {
                branches.put(segment.getPosition(), segment);
                branchAdded = true;

            }
        }

        if (!branchAdded) {
            finished = true;
        }

    }

    public Collection<Segment> getBranches () {
        return branches.values();
    }

    public Map<Vector, LeafCluster> positionToLeaves = new HashMap<>();

    public Collection<LeafCluster> getLeaves(){
        return positionToLeaves.values();
    }

}
