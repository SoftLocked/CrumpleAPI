package com.CrumpleAPI.crumpleapi.api.controller;

import com.CrumpleAPI.crumpleapi.api.model.TreeNode;
import com.CrumpleAPI.crumpleapi.service.CrumpleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CrumpleControl {

    private CrumpleService crumpleService;

    @Autowired
    public CrumpleControl(CrumpleService crumpleService) {
        this.crumpleService = crumpleService;
    }

    @GetMapping("/tree")
    public TreeNode getCrumple(@RequestParam String state) {
        Optional<TreeNode> tree = crumpleService.getCrumple(state);
        if (tree.isPresent()) {
            return tree.get();
        }
        return null;
    }

}
