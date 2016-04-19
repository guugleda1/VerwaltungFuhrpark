package ch.makery.address;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ch.makery.address.model.Fahrzeug;
import ch.makery.address.model.Mitarbeiter;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class XmlParser {

	public static ArrayList<Mitarbeiter> mitarbeiterSelectionSort(ArrayList<Mitarbeiter> mitarbeiter) {

		// for (int i = 0; i < mitarbeiter.size() - 1; i++)
		// {
		// int index = i;
		// for (int j = i + 1; j < mitarbeiter.size(); j++)
		// if (Integer.parseInt(mitarbeiter.get(j).getPersonalnumber()) <
		// Integer.parseInt(mitarbeiter.get(index).getPersonalnumber()))
		// index = j;
		//
		// int smallerNumber =
		// Integer.parseInt(mitarbeiter.get(index).getPersonalnumber());
		// mitarbeiter.get(index).setPersonalnumber(Integer.toString(i));
		// mitarbeiter.get(i).setPersonalnumber(Integer.toString(smallerNumber));
		// }
		return mitarbeiter;
	}

	public static void writeFahrzeugXml(ArrayList<Fahrzeug> fzArrList) {
		try {
			// write XML
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("cars");
			doc.appendChild(rootElement);

			for (Fahrzeug fahrzeug : fzArrList) {
				// supercars element
				Element unique = doc.createElement("unique");
				rootElement.appendChild(unique);

				// setting attribute to element
				Attr attr = doc.createAttribute("carNumber");
				attr.setValue(fahrzeug.getCarNumber());
				unique.setAttributeNode(attr);

				// carname element
				Element model = doc.createElement("model");
				model.appendChild(doc.createTextNode(fahrzeug.getModel()));
				Element licensePlate = doc.createElement("licensePlate");
				licensePlate.appendChild(doc.createTextNode(fahrzeug.getLicensePlate()));
				Element typ = doc.createElement("typ");
				typ.appendChild(doc.createTextNode(fahrzeug.getTyp()));
				Element essentialDriverslicense = doc.createElement("essentialDriverslicense");
				essentialDriverslicense.appendChild(doc.createTextNode(fahrzeug.getEssentialDriverslicense()));
				Element availability = doc.createElement("availability");
				availability.appendChild(doc.createTextNode(Boolean.toString(fahrzeug.getAvailability())));
				Element rentedBy = doc.createElement("rentedBy");
				Element rentedByPNO = doc.createElement("rentedByPNO");
				Element von = doc.createElement("von");
				Element bis = doc.createElement("bis");
				if (fahrzeug.getAvailability()) {
					rentedBy.appendChild(doc.createTextNode("-"));
					rentedByPNO.appendChild(doc.createTextNode("-"));
					von.appendChild(doc.createTextNode("-"));
					bis.appendChild(doc.createTextNode("-"));
				} else {
					rentedBy.appendChild(doc.createTextNode(fahrzeug.getRentedBy()));
					rentedByPNO.appendChild(doc.createTextNode(fahrzeug.getRentedByPNO()));
					von.appendChild(doc.createTextNode(fahrzeug.getVon().toString()));
					bis.appendChild(doc.createTextNode(fahrzeug.getBis().toString()));
				}
				Element wieOftGeliehen = doc.createElement("wieOftGeliehen");
				wieOftGeliehen.appendChild(doc.createTextNode(Integer.toString(fahrzeug.getWieOftGeliehen())));
				Element wieLangeGeliehen = doc.createElement("wieLangeGeliehen");
				wieLangeGeliehen.appendChild(doc.createTextNode(Integer.toString(fahrzeug.getWieLangeGeliehen())));

				unique.appendChild(model);
				unique.appendChild(licensePlate);
				unique.appendChild(typ);
				unique.appendChild(essentialDriverslicense);
				unique.appendChild(availability);
				unique.appendChild(rentedBy);
				unique.appendChild(rentedByPNO);
				unique.appendChild(von);
				unique.appendChild(bis);
				unique.appendChild(wieOftGeliehen);
				unique.appendChild(wieLangeGeliehen);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("cars.xml"));
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeMitarbeiterXml(ArrayList<Mitarbeiter> maArrList) {
		try {
			// write XML
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("employee");
			doc.appendChild(rootElement);

			ArrayList<Mitarbeiter> sortedMaArrList = mitarbeiterSelectionSort(maArrList);
			for (Mitarbeiter mitarbeiter : sortedMaArrList) {
				// supercars element
				Element unique = doc.createElement("unique");
				rootElement.appendChild(unique);

				// setting attribute to element
				Attr attr = doc.createAttribute("personalNumber");
				attr.setValue(mitarbeiter.getPersonalnumber());
				unique.setAttributeNode(attr);

				// carname element
				Element firstName = doc.createElement("firstName");
				firstName.appendChild(doc.createTextNode(mitarbeiter.getFirstName()));
				Element lastName = doc.createElement("lastName");
				lastName.appendChild(doc.createTextNode(mitarbeiter.getLastName()));
				Element birthday = doc.createElement("birthday");
				birthday.appendChild(doc.createTextNode(mitarbeiter.getBirthday()));
				Element driverslicense = doc.createElement("driverslicense");
				driverslicense.appendChild(doc.createTextNode(mitarbeiter.getDriverslicense()));
				Element ban = doc.createElement("ban");
				ban.appendChild(doc.createTextNode(Boolean.toString(mitarbeiter.getBan())));
				Element hatFahrzeug = doc.createElement("hatFahrzeug");
				hatFahrzeug.appendChild(doc.createTextNode(Boolean.toString(mitarbeiter.getHatFahrzeug())));
				Element rentedWhat = doc.createElement("rentedWhat");
				Element rentedWhatLicensePlate = doc.createElement("rentedWhatLicensePlate");
				Element von = doc.createElement("von");
				Element bis = doc.createElement("bis");
				if (mitarbeiter.getHatFahrzeug()) {
					rentedWhat.appendChild(doc.createTextNode(mitarbeiter.getRentedWhat()));
					rentedWhatLicensePlate.appendChild(doc.createTextNode(mitarbeiter.getRentedWhatLicensePlate()));
					von.appendChild(doc.createTextNode(mitarbeiter.getVon().toString()));
					bis.appendChild(doc.createTextNode(mitarbeiter.getBis().toString()));
				} else {
					rentedWhat.appendChild(doc.createTextNode("-"));
					rentedWhatLicensePlate.appendChild(doc.createTextNode("-"));
					von.appendChild(doc.createTextNode("-"));
					bis.appendChild(doc.createTextNode("-"));
				}

				unique.appendChild(firstName);
				unique.appendChild(lastName);
				unique.appendChild(driverslicense);
				unique.appendChild(ban);
				unique.appendChild(birthday);
				unique.appendChild(hatFahrzeug);
				unique.appendChild(rentedWhat);
				unique.appendChild(rentedWhatLicensePlate);
				unique.appendChild(von);
				unique.appendChild(bis);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("employees.xml"));
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Fahrzeug> readFahrzeugXml() {
		ArrayList<Fahrzeug> fzArrList = new ArrayList<Fahrzeug>();
		try {
			File inputFile = new File("cars.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("unique");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Node currentItem = nList.item(i);
					if (Boolean.valueOf((eElement.getElementsByTagName("availability").item(0).getTextContent()))) {
						LocalDate datevon = LocalDate.now();
						LocalDate datebis = LocalDate.now();
						fzArrList.add(new Fahrzeug(eElement.getElementsByTagName("model").item(0).getTextContent(),
								eElement.getElementsByTagName("licensePlate").item(0).getTextContent(),
								currentItem.getAttributes().getNamedItem("carNumber").getNodeValue(),
								eElement.getElementsByTagName("essentialDriverslicense").item(0).getTextContent(),
								eElement.getElementsByTagName("typ").item(0).getTextContent(),
								Boolean.valueOf(eElement.getElementsByTagName("availability").item(0).getTextContent()),
								eElement.getElementsByTagName("rentedBy").item(0).getTextContent(),
								eElement.getElementsByTagName("rentedByPNO").item(0).getTextContent(), datevon, datebis,
								Integer.parseInt(
										eElement.getElementsByTagName("wieOftGeliehen").item(0).getTextContent()),
								Integer.parseInt(
										eElement.getElementsByTagName("wieLangeGeliehen").item(0).getTextContent())));
					} else {
						LocalDate datevon = LocalDate
								.parse(eElement.getElementsByTagName("von").item(0).getTextContent(), formatter);
						LocalDate datebis = LocalDate
								.parse(eElement.getElementsByTagName("bis").item(0).getTextContent(), formatter);
						fzArrList.add(new Fahrzeug(eElement.getElementsByTagName("model").item(0).getTextContent(),
								eElement.getElementsByTagName("licensePlate").item(0).getTextContent(),
								currentItem.getAttributes().getNamedItem("carNumber").getNodeValue(),
								eElement.getElementsByTagName("essentialDriverslicense").item(0).getTextContent(),
								eElement.getElementsByTagName("typ").item(0).getTextContent(),
								Boolean.valueOf(eElement.getElementsByTagName("availability").item(0).getTextContent()),
								eElement.getElementsByTagName("rentedBy").item(0).getTextContent(),
								eElement.getElementsByTagName("rentedByPNO").item(0).getTextContent(), datevon, datebis,
								Integer.parseInt(
										eElement.getElementsByTagName("wieOftGeliehen").item(0).getTextContent()),
								Integer.parseInt(
										eElement.getElementsByTagName("wieLangeGeliehen").item(0).getTextContent())));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fzArrList;
	}

	public static ArrayList<Mitarbeiter> readMitarbeiterXml() {
		ArrayList<Mitarbeiter> maArrList = new ArrayList<Mitarbeiter>();
		try {
			File inputFile = new File("employees.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			NodeList nList = doc.getElementsByTagName("unique");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Node currentItem = nList.item(i);

					if (Boolean.valueOf(eElement.getElementsByTagName("hatFahrzeug").item(0).getTextContent())) {
						LocalDate datevon = LocalDate
								.parse(eElement.getElementsByTagName("von").item(0).getTextContent(), formatter);
						LocalDate datebis = LocalDate
								.parse(eElement.getElementsByTagName("bis").item(0).getTextContent(), formatter);
						maArrList.add(new Mitarbeiter(
								eElement.getElementsByTagName("firstName").item(0).getTextContent(),
								eElement.getElementsByTagName("lastName").item(0).getTextContent(),
								currentItem.getAttributes().getNamedItem("personalNumber").getNodeValue(),
								eElement.getElementsByTagName("driverslicense").item(0).getTextContent(),
								Boolean.valueOf(eElement.getElementsByTagName("ban").item(0).getTextContent()),
								eElement.getElementsByTagName("birthday").item(0).getTextContent(),
								Boolean.valueOf(eElement.getElementsByTagName("hatFahrzeug").item(0).getTextContent()),
								eElement.getElementsByTagName("rentedWhat").item(0).getTextContent(),
								eElement.getElementsByTagName("rentedWhatLicensePlate").item(0).getTextContent(),
								datevon, datebis));
					} else {
						LocalDate datevon = LocalDate.now();
						LocalDate datebis = LocalDate.now();
						maArrList.add(new Mitarbeiter(
								eElement.getElementsByTagName("firstName").item(0).getTextContent(),
								eElement.getElementsByTagName("lastName").item(0).getTextContent(),
								currentItem.getAttributes().getNamedItem("personalNumber").getNodeValue(),
								eElement.getElementsByTagName("driverslicense").item(0).getTextContent(),
								Boolean.valueOf(eElement.getElementsByTagName("ban").item(0).getTextContent()),
								eElement.getElementsByTagName("birthday").item(0).getTextContent(),
								Boolean.valueOf(eElement.getElementsByTagName("hatFahrzeug").item(0).getTextContent()),
								eElement.getElementsByTagName("rentedWhat").item(0).getTextContent(),
								eElement.getElementsByTagName("rentedWhatLicensePlate").item(0).getTextContent(),
								datevon, datebis));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maArrList;
	}

	// public static void writeStatistikXml(ArrayList<Fahrzeug> fzArrList) {
	// try {
	// // write XML
	// DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	// Document doc = dBuilder.newDocument();
	// // root element
	// Element rootElement = doc.createElement("cars");
	// doc.appendChild(rootElement);
	//
	// for (Fahrzeug fahrzeug : fzArrList) {
	// // supercars element
	// Element unique = doc.createElement("unique");
	// rootElement.appendChild(unique);
	//
	// // setting attribute to element
	// Attr attr = doc.createAttribute("carNumber");
	// attr.setValue(fahrzeug.getCarNumber());
	// unique.setAttributeNode(attr);
	//
	// // carname element
	// Element model = doc.createElement("model");
	// model.appendChild(doc.createTextNode(fahrzeug.getModel()));
	// Element licensePlate = doc.createElement("licensePlate");
	// licensePlate.appendChild(doc.createTextNode(fahrzeug.getLicensePlate()));
	// Element typ = doc.createElement("typ");
	// typ.appendChild(doc.createTextNode(fahrzeug.getTyp()));
	// Element essentialDriverslicense =
	// doc.createElement("essentialDriverslicense");
	// essentialDriverslicense.appendChild(doc.createTextNode(fahrzeug.getEssentialDriverslicense()));
	// Element availability = doc.createElement("availability");
	// availability.appendChild(doc.createTextNode(Boolean.toString(fahrzeug.getAvailability())));
	// Element rentedBy = doc.createElement("rentedBy");
	// rentedBy.appendChild(doc.createTextNode(fahrzeug.getRentedBy()));
	// Element von = doc.createElement("von");
	// von.appendChild(doc.createTextNode(fahrzeug.getVon().toString()));
	// Element bis = doc.createElement("bis");
	// bis.appendChild(doc.createTextNode(fahrzeug.getBis().toString()));
	//
	// unique.appendChild(model);
	// unique.appendChild(licensePlate);
	// unique.appendChild(typ);
	// unique.appendChild(essentialDriverslicense);
	// unique.appendChild(availability);
	// unique.appendChild(rentedBy);
	// unique.appendChild(von);
	// unique.appendChild(bis);
	// }
	//
	// // write the content into xml file
	// TransformerFactory transformerFactory = TransformerFactory.newInstance();
	// Transformer transformer = transformerFactory.newTransformer();
	// DOMSource source = new DOMSource(doc);
	// StreamResult result = new StreamResult(new File("cars.xml"));
	// transformer.transform(source, result);
	// // Output to console for testing
	// StreamResult consoleResult = new StreamResult(System.out);
	// transformer.transform(source, consoleResult);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
