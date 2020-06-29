package irisdeltaj.completerelational.br.unb.cic.iris.util;

import irisdeltaj.completerelational.br.unb.cic.iris.core.model.Category;

public class CategoryUtil {
	private static final String[] forums = new String[] { "group", "groups", "forum", "forums" };

	private static final String[] promotions = new String[] { "discount", "coupon", "@aliexpress.com", "aliexpress",
			"aproveite", "voegol", "casasbahia", "@ebay.com", "ebay", "extra", "pontofrio", "dafiti", "net", "claro",
			"vivo", "@shopfacil.com", "shop fácil", "cuponomia", "utsavfashion", "indiarush", "koovs", "apple",
			"mintra", "topshop", "gucci", "chanel", "netshoes", "mercadolivre", "burberry", "valentino", "sales",
			"sale", "americanas", "walmart", "submarino", "saraiva", "centauro", "hurb", "hotel urbano", "passarela",
			"amazon", "shoptime", "vivara", "magalu", "magazineluiza", "magazine luiza", "ricardo eletro",
			"ricardoeletro", "aliexpress", "saraiva", "samsung", "hp", "offer", "free", "% off", "produtos", "products",
			"clothes", "economize", "oferta", "desconto", "microsoft", "promotions", "ifood", "uber eats", "uber" };

	private static final String[] social = new String[] { "social", "@youtube.com", "youtube", "@linkedin.com",
			"pinterest", "reedit", "linkedin", "@facebook.com", "facebook", "@twitter.com", "twitter", "instagram",
			"telegram", "whatsapp", "tinder", "wechat", "happn", "weibo", "viber", "liner", "skype", "douban", "tiktok",
			"kzone", "snapchat" };

	private static final String[] primary = new String[] { "@gmail.", "@hotmail.", "@outlook.", "@yahoo.", "@unb.br",
			"@bol.com", "@mail.com", "@usp.", "mestrado", "doutorado", "msc", "phd", "meet", "reunião", "class", "aula",
			"estudo", "study", "sei", "cade", "projeto", "project", "agenda", "calendar", "@contato", "noreply@",
			"no-reply@", "contact@", "mail", "edu.br" };

	public static String defineCategory(String message, String subject, String from) {
		if (stringContainsItemFromList(from, forums) || stringContainsItemFromList(subject, forums)
				|| stringContainsItemFromList(message, forums)) {
			return Category.FORUMS;
		} else if (stringContainsItemFromList(from, social) || stringContainsItemFromList(subject, social)
				|| stringContainsItemFromList(message, social)) {
			return Category.SOCIAL;
		} else if (stringContainsItemFromList(from, promotions) || stringContainsItemFromList(subject, promotions)
				|| stringContainsItemFromList(message, promotions)) {
			return Category.PROMOTIONS;
		} else if (stringContainsItemFromList(from, primary) || stringContainsItemFromList(subject, primary)
				|| stringContainsItemFromList(message, primary)) {
			return Category.PRIMARY;
		} else {
			return Category.UPDATES;
		}
	}

	private static boolean stringContainsItemFromList(String inputStr, String[] items) {
		for (int i = 0; i < items.length; i++) {
			if (inputStr.toLowerCase().contains(items[i].toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
