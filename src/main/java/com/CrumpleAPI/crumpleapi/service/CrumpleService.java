package com.CrumpleAPI.crumpleapi.service;

import com.CrumpleAPI.crumpleapi.api.model.CrumpleTree;
import com.CrumpleAPI.crumpleapi.api.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CrumpleService {

    CrumpleTree tree;

    public CrumpleService() {
    }


    public Optional<TreeNode> getCrumple(String stateString) {

        tree = new CrumpleTree();

        ArrayList values = new ArrayList();
        for (int i = 0; i < stateString.length(); i++) {
            if ('0' <= stateString.charAt(i) && stateString.charAt(i) <= '9') {
                int j = i;
                for (; j < stateString.length(); j++) {
                    if (stateString.charAt(j) == ',' || stateString.charAt(j) == ']') {
                        int temp = Integer.parseInt(stateString.substring(i, j));
                        values.add(temp);
                        break;
                    }
                }
                i = j;
            }
        }

        for (int i = 0; i < values.size(); i++) {
            if (!tree.contains((int)values.get(i))) {
                tree.insert((int)values.get(i));
            } else {
                tree.remove((int)values.get(i));
            }
        }

        Optional optional = Optional.of(tree.root);
        return optional;
    }
}
