package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Frete {
    private  String valor;
    private  String prazo;
    private String entregaDomiciliar;
    private String entregaFinais;


    public double getValor() {
        return Double.parseDouble(valor.replace(",","."));
    }

    public String getPrazo() {
        return prazo;
    }

    public String getEntregaDomiciliar() {
        return entregaDomiciliar;
    }

    public String getEntregaFinais() {
        return entregaFinais;
    }

    public Frete(String cpe) throws IOException, ParserConfigurationException, SAXException {
        resultadoFrete(solictaCalc(cpe));
    }


    public StringBuffer solictaCalc(String cep) throws IOException {
        URL url = new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=08082650&sDsSenha=564321&sCepOrigem=31360510&sCepDestino=" + cep + "&nVlPeso=1&nCdFormato=1&nVlComprimento=20&nVlAltura=20&nVlLargura=20&sCdMaoPropria=n&nVlValorDeclarado=0&sCdAvisoRecebimento=n&nCdServico=04510&nVlDiametro=0&StrRetorno=xml&nIndicaCalculo=3");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        StringBuffer content = new StringBuffer();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            String inputLine;
            while ((inputLine = bf.readLine()) != null) {
                content.append(inputLine);
            }
        }
        conexao.disconnect();
        return content;
    }

    public void resultadoFrete(StringBuffer content) throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(content.toString())));
        NodeList value = doc.getElementsByTagName("Servicos");
        if (value.getLength() > 0) {
            Element element = (Element) value.item(0);
            this.valor = element.getElementsByTagName("Valor").item(0).getTextContent();
            this.prazo = element.getElementsByTagName("PrazoEntrega").item(0).getTextContent();
            this.entregaDomiciliar = element.getElementsByTagName("EntregaDomiciliar").item(0).getTextContent();
            this.entregaFinais = element.getElementsByTagName("EntregaSabado").item(0).getTextContent();
        } else {
            System.out.println("Error na leitura de informações");
        }
    }




    public String toString() {
        return "Valor: " + "R$"+ this.valor + "\n" +
                "Prazo: " + this.prazo + " dias" + "\n" +
                "Entrega Domiciliar: " + this.entregaDomiciliar + "\n" +
                "Entraga finais de semana: " + this.entregaFinais;
    }
}
