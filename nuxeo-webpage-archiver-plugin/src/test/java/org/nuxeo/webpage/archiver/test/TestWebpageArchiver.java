/*
 * (C) Copyright 2016 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     thibaud
 */
package org.nuxeo.webpage.archiver.test;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.automation.test.EmbeddedAutomationServerFeature;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.platform.commandline.executor.api.CommandAvailability;
import org.nuxeo.ecm.platform.commandline.executor.api.CommandLineExecutorService;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.webpage.archiver.WebpageToBlob;

/**
 * 
 * @since 7.10HF05
 */
@RunWith(FeaturesRunner.class)
@Features({ AutomationFeature.class })
@Deploy({ "nuxeo-webpage-archiver", "org.nuxeo.ecm.platform.commandline.executor" })
public class TestWebpageArchiver {
    
    protected static final Log log = LogFactory.getLog(TestWebpageArchiver.class);

    @Before
    public void setUp() {
    }

    @After
    public void cleanup() {

    }
    
    @Test
    public void testUrlToPdf() throws Exception {
        
        CommandLineExecutorService cles = Framework.getService(CommandLineExecutorService.class);
        CommandAvailability ca = cles.getCommandAvailability(WebpageToBlob.COMMANDLINE_wkhtmltopdf);
        Assume.assumeTrue("wkhtmltopdf is not available, skipping test", ca.isAvailable());
        
        Blob result = null;
        result = WebpageToBlob.toPdf("https://en.wikipedia.org/wiki/Unit_testing", null);
        assertNotNull(result);
    }

}
