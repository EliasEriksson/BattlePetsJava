package Elements;

import java.util.Arrays;

public enum Element {
    typeLess(new Elements[] {
    }, new Elements[] {
    }, new Elements[] {
    }, new Elements[]{
    }),

    normal(new Elements[] {
    }, new Elements[] {
    }, new Elements[] {
    }, new Elements[]{
            Elements.ghost
    }),

    water(new Elements[] {
            Elements.grass
    }, new Elements[] {
            Elements.water
    }, new Elements[] {
            Elements.fire
    }, new Elements[]{
    }),

    grass(new Elements[] {
            Elements.fire
    }, new Elements[] {
            Elements.grass
    }, new Elements[] {
            Elements.water
    }, new Elements[]{
    }),

    fire(new Elements[] {
            Elements.water
    }, new Elements[] {
            Elements.fire
    }, new Elements[] {
            Elements.grass
    }, new Elements[]{
    }),

    electric(new Elements[] {
    }, new Elements[] {
            Elements.electric
    }, new Elements[] {
            Elements.water
    }, new Elements[]{
    }),

    fighting(new Elements[] {
    }, new Elements[] {
    }, new Elements[] {
            Elements.normal
    }, new Elements[]{
    });

    private Elements[] weaknesses;
    private Elements[] resistant;
    private Elements[] effective;
    private Elements[] immune;

    Element(Elements[] weaknesses, Elements[] resistant, Elements[] effective, Elements[] immune){
        this.weaknesses = weaknesses;
        this.resistant = resistant;
        this.effective = effective;
        this.immune = immune;
    }

    public boolean is_super_effective(Element element){
        for (Elements effectiveElement : this.effective) {
            if (effectiveElement.name().equals(element.name())){
                return true;
            }
        }
        return false;
    }

    public boolean is_resistant(Element element){
        for (Elements resistantElement : this.resistant) {
            if (resistantElement.name().equals(element.name())){
                return true;
            }
        }
        return false;
    }

    public boolean is_immune(Element element){
        for (Elements imminityElement : this.immune) {
            if (imminityElement.name().equals(element.name())){
                return true;
            }
        }
        return false;
    }

    public void view(){
        System.out.println("Weaknesses: " + Arrays.toString(this.weaknesses));
        System.out.println("Resistant: " + Arrays.toString(this.resistant));
        System.out.println("Effective: " + Arrays.toString(this.effective));
    }

    public static boolean contains(Element[] elementArray, Element elementToCheck){
        for (Element element : elementArray) {
            if (elementToCheck == element) {
                return true;
            }
        }
        return false;
    }

    enum Elements {
        normal,
        grass,
        fire,
        water,
        electric,
        fighting,
        flying,
        ice,
        poison,
        psycich,
        dark,
        ghost;

        public String toString() {
            return this.name();
        }
    }
}
