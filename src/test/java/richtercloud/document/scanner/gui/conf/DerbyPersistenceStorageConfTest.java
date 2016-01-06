/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.document.scanner.gui.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.derby.jdbc.EmbeddedDriver;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richtercloud.document.scanner.gui.conf.model.TestClass;
import richtercloud.document.scanner.storage.DerbyPersistenceStorage;
import richtercloud.reflection.form.builder.message.LoggerMessageHandler;
import richtercloud.reflection.form.builder.message.MessageHandler;

/**
 *
 * @author richter
 */
public class DerbyPersistenceStorageConfTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(DerbyPersistenceStorageConfTest.class);

    /**
     * Test of getStorage method, of class DerbyPersistenceStorageConf.
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetStorage() throws FileNotFoundException {
        DerbyPersistenceStorageConf instance = new DerbyPersistenceStorageConf();
        DerbyPersistenceStorage result = instance.getStorage();
        assertNotNull(result);
    }

    /**
     * Test of validate method, of class DerbyPersistenceStorageConf. Mocking
     * EntityManager or Metamodel is overly hard in comparison to an integration
     * test.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testValidate() throws IOException, SQLException, InstantiationException, IllegalAccessException {
        Map<Object,Object> entityManagerFactoryProperties = new HashMap<>();
        File connTempFile = File.createTempFile("document-scanner-test", null);
        connTempFile.delete(); //don't create the directory because derby fails
            //otherwise
        LOGGER.info(String.format("using temporary directory '%s' for database storage", connTempFile.getAbsolutePath()));
        entityManagerFactoryProperties.put("javax.persistence.jdbc.url",
                String.format("jdbc:derby:%s;create=true", connTempFile.getAbsolutePath()) //create parameter is always true because using a temporary
                //directory
        );
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("document-scanner-test", entityManagerFactoryProperties);
        Class<?> driver = EmbeddedDriver.class; //this declaration facilitates
        //dependency management with an IDE with maven support and doesn't cause
        //any harm
        driver.newInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        MessageHandler messageHandler = new LoggerMessageHandler(LOGGER);
        Set<Class<?>> entityClasses = new HashSet<Class<?>>(Arrays.asList(TestClass.class));
        File lastSchemeStorageTempFile = File.createTempFile("document-scanner-test", null);
        lastSchemeStorageTempFile.delete(); //needs to be inexisting to trigger generation of default values in file
        LOGGER.info(String.format("using '%s' for temporary storage of last scheme", lastSchemeStorageTempFile.getAbsolutePath()));
        DerbyPersistenceStorageConf instance = new DerbyPersistenceStorageConf(entityManager,
                messageHandler,
                entityClasses,
                lastSchemeStorageTempFile //prevent creating file with TestClass which isn't accessible outside tests
        );
        try {
            instance.validate(); //stores the metamodel into file
        } catch(StorageConfInitializationException ex) {
            fail();
        }
        try {
            instance.validate(); //compares with stored metamodel
        } catch(StorageConfInitializationException ex) {
            fail();
        }
        //@TODO: change class to trigger recognition of metamodel change (asked
        //http://stackoverflow.com/questions/34218150/how-to-simulate-a-class-change-in-an-jpa-integration-test for inputs)
        /*expResult = false;
        result = instance.validate();
        assertEquals(expResult, result);*/
    }

}
