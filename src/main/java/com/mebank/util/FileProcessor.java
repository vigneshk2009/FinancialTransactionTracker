package com.mebank.util;

import com.mebank.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface FileProcessor {

    List<Transaction> processFileData(String fileName) throws IOException;
}
