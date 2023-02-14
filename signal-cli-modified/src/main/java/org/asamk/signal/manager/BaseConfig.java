package org.asamk.signal.manager;

import org.whispersystems.signalservice.api.push.TrustStore;
import org.whispersystems.signalservice.internal.configuration.SignalCdnUrl;
import org.whispersystems.signalservice.internal.configuration.SignalContactDiscoveryUrl;
import org.whispersystems.signalservice.internal.configuration.SignalServiceConfiguration;
import org.whispersystems.signalservice.internal.configuration.SignalServiceUrl;

public class BaseConfig {

    public final static String PROJECT_NAME = Manager.class.getPackage().getImplementationTitle();
    public final static String PROJECT_VERSION = Manager.class.getPackage().getImplementationVersion();

    final static String USER_AGENT = PROJECT_NAME == null ? null : PROJECT_NAME + " " + PROJECT_VERSION;
    final static String UNIDENTIFIED_SENDER_TRUST_ROOT = "BXu6QIKVz5MA8gstzfOgRQGqyLqOwNKHL6INkv3IHWMF";
    final static int PREKEY_MINIMUM_COUNT = 20;
    final static int PREKEY_BATCH_SIZE = 100;
    final static int MAX_ATTACHMENT_SIZE = 150 * 1024 * 1024;

    // Change this to the domain for your computer.
    private final static String URL = "https://signal.example.com:8082";
    private final static String CDN_URL = "https://cdn.signal.org";
    private final static TrustStore TRUST_STORE = new WhisperTrustStore();

    //// These are configuration options for the attack detection
    // Change this one if you are not running Signal CLI on the same machine as the server
    public final static String CONIKS_SERVER_URL = "127.0.0.1";

    // Tor defaults to running up a SOCKS5 proxy on port 9050
    // This should only need changing if tor is configured to do something else.
    public final static String TOR_SOCKS = "127.0.0.1:9050";

    // The tor hostname for your server will need to go here.
    // This will be located in <mitm-docker>/data/tor-hostname after running tor
    //   in the container. It will be copied automatically by the tor-start.sh script.
    public final static String TOR_HOSTNAME = "exampleonionhostname.onion";

    final static SignalServiceConfiguration serviceConfiguration = new SignalServiceConfiguration(
            new SignalServiceUrl[]{new SignalServiceUrl(URL, TRUST_STORE)},
            new SignalCdnUrl[]{new SignalCdnUrl(CDN_URL, TRUST_STORE)},
            new SignalContactDiscoveryUrl[0]
    );

    private BaseConfig() {
    }
}
