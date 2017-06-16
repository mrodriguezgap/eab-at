package com.gap.atpractice.testLinkAccess;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

import java.net.URL;

/**
 * Created by manuel on 6/14/17.
 */
public class TestLinkAccess extends TestLinkAPI {
    public TestLinkAccess(URL url, String devKey) throws TestLinkAPIException {
        super(url, devKey);
    }

}
