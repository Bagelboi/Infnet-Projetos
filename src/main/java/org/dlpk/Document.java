package org.dlpk;

public abstract class Document {
    public abstract void print();

    public static class PDF extends Document {
        @Override
        public void print() {
            System.out.println("Printing PDF");
        }
    }

    public static class HTML extends Document {
        @Override
        public void print() {
            System.out.println("Printing HTML");
        }
    }

    public static class Unknown extends Document {
        @Override
        public void print() {
            System.out.println("Unknown format");
        }
    }

    public enum TYPE {
        PDF,
        HTML,
        UNKNOWN
    }

    public static class Processor {
        private Document document;

        public Processor(TYPE type) {
            switch (type) {
                case PDF -> {
                    document = new PDF();
                }

                case HTML -> {
                    document = new HTML();
                }

                default -> {
                    document = new Unknown();
                }
            }
        }

        public void print() {
            document.print();
        }
    }
}