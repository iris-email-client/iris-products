package irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.mail.provider;

import java.util.List;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.BaseManager;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.mail.EmailProvider;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.mail.provider.GmailProvider;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.mail.provider.YahooProvider;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.mail.provider.OutlookProvider;
/*** added by dBaseMail* modified by dGmailProvider* modified by
dYahooProvider* modified by dOutlookProvider
 */
public class ProviderManager {
	private static ProviderManager instance = new ProviderManager();
	private BaseManager<EmailProvider> manager;
	private ProviderManager() {
		manager = new BaseManager<EmailProvider>();
		doAddProviders();
	}
	/*** modified by dGmailProvider* modified by dYahooProvider* modified by
	dOutlookProvider
	 */
	public void doAddProviders() {
		doAddProviders_original3();
		addProvider(new OutlookProvider());
	}
	public static ProviderManager instance() {
		return instance;
	}
	@SuppressWarnings("boxing")
	public void addProvider(EmailProvider provider) {
		manager.add(provider.getName().trim(), provider);
	}
	@SuppressWarnings("boxing")
	public EmailProvider getProvider(String name) {
		return manager.get(name);
	}
	public List<EmailProvider> getProviders() {
		return manager.getAll();
	}
	/*** modified by dGmailProvider
	 */
	public void doAddProviders_original0() {
	}
	/*** modified by dGmailProvider* modified by dYahooProvider
	 */
	public void doAddProviders_original2() {
		doAddProviders_original0();
		addProvider(new GmailProvider());
	}
	/*** modified by dGmailProvider* modified by dYahooProvider* modified by
	dOutlookProvider
	 */
	public void doAddProviders_original3() {
		doAddProviders_original2();
		addProvider(new YahooProvider());
	}
}