package Snapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Map {
    static int INF = Integer.MAX_VALUE;
    static URL url = Map.class.getResource("graph.txt");

    static int shortPath(int start, int end, ArrayList<Integer> path) throws NoPathException {
        int ret = -1;
        File file = new File(url.getPath());

        try {
            Scanner sc = new Scanner(file);
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();

            java.util.Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int i = 0; i < 2509; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, w});
                graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, w});
            }

            int n = 1000;
            int m = 2509;
            int[] dist = new int[n + 1];
            Arrays.fill(dist, INF);
            dist[start] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{start, 0});
            java.util.Map<Integer, Integer> prev = new HashMap<>();
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int node = curr[0];
                int d = curr[1];
                if (d > dist[node]) continue;
                for (int[] neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                    int v = neighbor[0];
                    int w = neighbor[1];
                    if (dist[node] + w < dist[v]) {
                        dist[v] = dist[node] + w;
                        pq.offer(new int[]{v, dist[v]});
                        prev.put(v, node);
                    }
                }
            }

            if (dist[end] == INF) {

                throw new NoPathException();

            } else {

                path = new ArrayList<>();

                int curr = end;

                while (curr != start) {
                    path.add(curr);
                    curr = prev.get(curr);
                }
                path.add(start);
                Collections.reverse(path);
//                System.out.printf("Shortest path from %d to %d: %d\n", start, end, dist[end]);
//                System.out.print("Nodes in path: ");
//                for (int node : path) {
//                    System.out.print(node + " ");
//                }
//                System.out.println();
                ret = dist[end];
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    static int ETA(int start, int end) throws NoPathException {
        ArrayList<Integer> m = new ArrayList<>();
        return shortPath(start, end, m);
    }

    static ArrayList<Integer> path(int start, int end) throws NoPathException {
        ArrayList<Integer> path = new ArrayList<>();
        int ret = -1;
        File file = new File(url.getPath());
        try {
            Scanner sc = new Scanner(file);
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();

            java.util.Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int i = 0; i < 2509; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, w});
                graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, w});
            }

            int n = 1000;
            int m = 2509;
            int[] dist = new int[n + 1];
            Arrays.fill(dist, INF);
            dist[start] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{start, 0});
            java.util.Map<Integer, Integer> prev = new HashMap<>();
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int node = curr[0];
                int d = curr[1];
                if (d > dist[node]) continue;
                for (int[] neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                    int v = neighbor[0];
                    int w = neighbor[1];
                    if (dist[node] + w < dist[v]) {
                        dist[v] = dist[node] + w;
                        pq.offer(new int[]{v, dist[v]});
                        prev.put(v, node);
                    }
                }
            }

            if (dist[end] == INF) {
                throw new NoPathException();

            } else {

                path = new ArrayList<>();

                int curr = end;

                while (curr != start) {
                    path.add(curr);
                    curr = prev.get(curr);
                }
                path.add(start);
                Collections.reverse(path);
//                System.out.printf("Shortest path from %d to %d: %d\n", start, end, dist[end]);
//                System.out.print("Nodes in path: ");
//                for (int node : path) {
//                    System.out.print(node + " ");
//                }
//                System.out.println();
                ret = dist[end];
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    static public class NoPathException extends Exception {
        NoPathException() {
            super("[Error] There is no path between the selected points!");
        }
    }
}
