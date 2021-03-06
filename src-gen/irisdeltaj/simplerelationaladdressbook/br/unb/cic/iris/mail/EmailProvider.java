package irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.mail;

import java.util.Properties;
/*** added by dBaseMail
 */
public interface EmailProvider extends Cloneable {
	public String getName();
	public String getDescription();
	public Properties getProperties();
	public String getTransportHost();
	public int getTransportPort();
	public String getTransportProtocol();
	public String getStoreHost();
	public int getStorePort();
	public String getStoreProtocol();
	public boolean isAuthenticationEnabled();
	public EmailProvider clone() throws CloneNotSupportedException;
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
}