function State() {
	var states = ['Alabama','Alaska','American Samoa','Arizona','Arkansas','California','Colorado','Connecticut','Delaware','District of Columbia','Federated States of Micronesia','Florida','Georgia','Guam','Hawaii','Idaho','Illinois','Indiana','Iowa','Kansas','Kentucky','Louisiana','Maine','Marshall Islands','Maryland','Massachusetts','Michigan','Minnesota','Mississippi','Missouri','Montana','Nebraska','Nevada','New Hampshire','New Jersey','New Mexico','New York','North Carolina','North Dakota','Northern Mariana Islands','Ohio','Oklahoma','Oregon','Palau','Pennsylvania','Puerto Rico','Rhode Island','South Carolina','South Dakota','Tennessee','Texas','Utah','Vermont','Virgin Island','Virginia','Washington','West Virginia','Wisconsin','Wyoming']
	var len = states.length;
	return (states[Math.floor((Math.random() * len + 0))]);
}


function rndID(min) {
	return Math.floor((Math.random() * 10000 + min));
}

function rndNumber(min) {
	return Math.floor((Math.random() * 1000 + min));
}

function rndCompany() {

	var companies = ["Microsoft","Tesla","Bank of America","Citibank","Starbucks","CenturyLink","Amazon","Diebold","Pfizer","Procter and Gamble","International Business Machines","Birkshire Hathaway","Abbott Laboratories","Coca Cola","Nvidia", "JP Morgan","Store Capital","Hunnington Bank","Marriott","Hilton","IHG International","Apple","Disney","Boeing","American Airlines","Delta Airlines","General Motors","Ford Motor Company","Salesforce","Facebook","Google"]
	var len = companies.length;
	return (companies[Math.floor((Math.random() * len + 0))]);
}

function rndRegion() {

	var region = ["North East","North West","South East","South West","Midwest","Central","South"];
	var len = region.length;
	return (region[Math.floor((Math.random() * len + 0))]);
}

function rndDate(start, end) {

	let current_datetime = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
	let formatted_date = current_datetime.getDate() + "/" + (current_datetime.getMonth() + 1) + "/" + current_datetime.getFullYear()

	return formatted_date;

}

function rndInvoice(min) {

	return "A-" + Math.floor((Math.random() * 100000 + min));
	
}

function rndMoney() {

	var dollar = Math.floor((Math.random() * 100000 + 1000));

	return formatMoney(dollar,2,".",",");
}

function formatMoney(number, decPlaces, decSep, thouSep) {

	decPlaces = isNaN(decPlaces = Math.abs(decPlaces)) ? 2 : decPlaces,
	decSep = typeof decSep === "undefined" ? "." : decSep;
	thouSep = typeof thouSep === "undefined" ? "," : thouSep;

	var sign = number < 0 ? "-" : "$";
	var i = String(parseInt(number = Math.abs(Number(number) || 0).toFixed(decPlaces)));
	var j = (j = i.length) > 3 ? j % 3 : 0;

	return sign +
		(j ? i.substr(0, j) + thouSep : "") +
		i.substr(j).replace(/(\decSep{3})(?=\decSep)/g, "$1" + thouSep) +
		(decPlaces ? decSep + Math.abs(number - i).toFixed(decPlaces).slice(2) : "");
}

function Country() {

	const countries = [
		"Afghanistan",
		"Albania",
		"Algeria",
		"American Samoa",
		"Andorra",
		"Angola",
		"Anguilla",
		"Antarctica",
		"Antigua and Barbuda",
		"Argentina",
		"Armenia",
		"Aruba",
		"Australia",
		"Austria",
		"Azerbaijan",
		"Bahamas (the)",
		"Bahrain",
		"Bangladesh",
		"Barbados",
		"Belarus",
		"Belgium",
		"Belize",
		"Benin",
		"Bermuda",
		"Bhutan",
		"Bolivia (Plurinational State of)",
		"Bonaire, Sint Eustatius and Saba",
		"Bosnia and Herzegovina",
		"Botswana",
		"Bouvet Island",
		"Brazil",
		"British Indian Ocean Territory (the)",
		"Brunei Darussalam",
		"Bulgaria",
		"Burkina Faso",
		"Burundi",
		"Cabo Verde",
		"Cambodia",
		"Cameroon",
		"Canada",
		"Cayman Islands (the)",
		"Central African Republic (the)",
		"Chad",
		"Chile",
		"China",
		"Christmas Island",
		"Cocos (Keeling) Islands (the)",
		"Colombia",
		"Comoros (the)",
		"Congo (the Democratic Republic of the)",
		"Congo (the)",
		"Cook Islands (the)",
		"Costa Rica",
		"Croatia",
		"Cuba",
		"Curaçao",
		"Cyprus",
		"Czechia",
		"Côte d'Ivoire",
		"Denmark",
		"Djibouti",
		"Dominica",
		"Dominican Republic (the)",
		"Ecuador",
		"Egypt",
		"El Salvador",
		"Equatorial Guinea",
		"Eritrea",
		"Estonia",
		"Eswatini",
		"Ethiopia",
		"Falkland Islands (the) [Malvinas]",
		"Faroe Islands (the)",
		"Fiji",
		"Finland",
		"France",
		"French Guiana",
		"French Polynesia",
		"French Southern Territories (the)",
		"Gabon",
		"Gambia (the)",
		"Georgia",
		"Germany",
		"Ghana",
		"Gibraltar",
		"Greece",
		"Greenland",
		"Grenada",
		"Guadeloupe",
		"Guam",
		"Guatemala",
		"Guernsey",
		"Guinea",
		"Guinea-Bissau",
		"Guyana",
		"Haiti",
		"Heard Island and McDonald Islands",
		"Holy See (the)",
		"Honduras",
		"Hong Kong",
		"Hungary",
		"Iceland",
		"India",
		"Indonesia",
		"Iran (Islamic Republic of)",
		"Iraq",
		"Ireland",
		"Isle of Man",
		"Israel",
		"Italy",
		"Jamaica",
		"Japan",
		"Jersey",
		"Jordan",
		"Kazakhstan",
		"Kenya",
		"Kiribati",
		"Korea (the Democratic People's Republic of)",
		"Korea (the Republic of)",
		"Kuwait",
		"Kyrgyzstan",
		"Lao People's Democratic Republic (the)",
		"Latvia",
		"Lebanon",
		"Lesotho",
		"Liberia",
		"Libya",
		"Liechtenstein",
		"Lithuania",
		"Luxembourg",
		"Macao",
		"Madagascar",
		"Malawi",
		"Malaysia",
		"Maldives",
		"Mali",
		"Malta",
		"Marshall Islands (the)",
		"Martinique",
		"Mauritania",
		"Mauritius",
		"Mayotte",
		"Mexico",
		"Micronesia (Federated States of)",
		"Moldova (the Republic of)",
		"Monaco",
		"Mongolia",
		"Montenegro",
		"Montserrat",
		"Morocco",
		"Mozambique",
		"Myanmar",
		"Namibia",
		"Nauru",
		"Nepal",
		"Netherlands (the)",
		"New Caledonia",
		"New Zealand",
		"Nicaragua",
		"Niger (the)",
		"Nigeria",
		"Niue",
		"Norfolk Island",
		"Northern Mariana Islands (the)",
		"Norway",
		"Oman",
		"Pakistan",
		"Palau",
		"Palestine, State of",
		"Panama",
		"Papua New Guinea",
		"Paraguay",
		"Peru",
		"Philippines (the)",
		"Pitcairn",
		"Poland",
		"Portugal",
		"Puerto Rico",
		"Qatar",
		"Republic of North Macedonia",
		"Romania",
		"Russian Federation (the)",
		"Rwanda",
		"Réunion",
		"Saint Barthélemy",
		"Saint Helena, Ascension and Tristan da Cunha",
		"Saint Kitts and Nevis",
		"Saint Lucia",
		"Saint Martin (French part)",
		"Saint Pierre and Miquelon",
		"Saint Vincent and the Grenadines",
		"Samoa",
		"San Marino",
		"Sao Tome and Principe",
		"Saudi Arabia",
		"Senegal",
		"Serbia",
		"Seychelles",
		"Sierra Leone",
		"Singapore",
		"Sint Maarten (Dutch part)",
		"Slovakia",
		"Slovenia",
		"Solomon Islands",
		"Somalia",
		"South Africa",
		"South Georgia and the South Sandwich Islands",
		"South Sudan",
		"Spain",
		"Sri Lanka",
		"Sudan (the)",
		"Suriname",
		"Svalbard and Jan Mayen",
		"Sweden",
		"Switzerland",
		"Syrian Arab Republic",
		"Taiwan (Province of China)",
		"Tajikistan",
		"Tanzania, United Republic of",
		"Thailand",
		"Timor-Leste",
		"Togo",
		"Tokelau",
		"Tonga",
		"Trinidad and Tobago",
		"Tunisia",
		"Turkey",
		"Turkmenistan",
		"Turks and Caicos Islands (the)",
		"Tuvalu",
		"Uganda",
		"Ukraine",
		"United Arab Emirates (the)",
		"United Kingdom of Great Britain and Northern Ireland (the)",
		"United States Minor Outlying Islands (the)",
		"United States of America (the)",
		"Uruguay",
		"Uzbekistan",
		"Vanuatu",
		"Venezuela (Bolivarian Republic of)",
		"Viet Nam",
		"Virgin Islands (British)",
		"Virgin Islands (U.S.)",
		"Wallis and Futuna",
		"Western Sahara",
		"Yemen",
		"Zambia",
		"Zimbabwe",
		"Åland Islands"
	];

	var len = countries.length;
	return (countries[Math.floor((Math.random() * len + 0))]);

}

function firstname() {
	var names = [
		"Noah",
		"Emma",
		"Liam",
		"Sophia",
		"Jacob",
		"Olivia",
		"Mason",
		"Isabella",
		"William",
		"Ava",
		"Ethan",
		"Mia",
		"Michael",
		"Abigail",
		"Alexander",
		"Emily",
		"James",
		"Madison",
		"Elijah",
		"Charlotte",
		"Daniel",
		"Elizabeth",
		"Benjamin",
		"Amelia",
		"Aiden",
		"Chloe",
		"Jayden",
		"Ella",
		"Logan",
		"Evelyn",
		"Matthew",
		"Avery",
		"David",
		"Sofia",
		"Joseph",
		"Harper",
		"Lucas",
		"Grace",
		"Jackson",
		"Addison",
		"Anthony",
		"Victoria",
		"Joshua",
		"Natalie",
		"Samuel",
		"Lily",
		"Andrew",
		"Aubrey",
		"Gabriel",
		"Lillian",
		"Christopher",
		"Zoey",
		"John",
		"Hannah",
		"Dylan",
		"Layla",
		"Carter",
		"Brooklyn",
		"Isaac",
		"Samantha",
		"Ryan",
		"Zoe",
		"Luke",
		"Leah",
		"Oliver",
		"Scarlett",
		"Nathan",
		"Riley",
		"Henry",
		"Camila",
		"Owen",
		"Savannah",
		"Caleb",
		"Anna",
		"Wyatt",
		"Audrey",
		"Christian",
		"Allison",
		"Sebastian",
		"Aria"
	]
	var len = names.length;
	return (names[Math.floor((Math.random() * len + 0))]);
}

function lastname() {
	var names = [
		"Smith",
		"Jones",
		"Williams",
		"Brown",
		"Daviss",
		"Miller",
		"Wilson",
		"Taylor",
		"Anderson",
		"Thomas",
		"Moore",
		"Jackson",
		"White",
		"Harris",
		"Martin",
		"Thompson",
		"Garcia",
		"Clark",
		"Wright",
		"Hill",
		"Adams"
	]
	var len = names.length;
	return (names[Math.floor((Math.random() * len + 0))]);
}

function completeName() {
	return firstname() + " " + lastname();
}

function rndParkingProduct() {

	var product = ["24/7 Non Reserved","24/7 Reserved","Weekday Reserved","VIP Reserved"];
	var len = product.length;
	return (product[Math.floor((Math.random() * len + 0))]);
	
}

function todayDate() {
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	today = mm + '/' + dd + '/' + yyyy;
	return(today);
}

