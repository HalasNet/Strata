/*
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.basics.index;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.impl.direct.DirectPrivateBeanBuilder;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.basics.currency.CurrencyPair;

/**
 * Information about a single observation of an FX index.
 * <p>
 * Observing an FX index requires knowledge of the index, fixing date and maturity date.
 */
@BeanDefinition(builderScope = "private", constructorScope = "package")
public final class FxIndexObservation
    implements IndexObservation, ImmutableBean, Serializable {

  /**
   * The FX index.
   * <p>
   * The rate will be queried from this index.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final FxIndex index;
  /**
   * The date of the index fixing.
   * <p>
   * This is an adjusted date with any business day rule applied.
   * Valid business days are defined by {@link FxIndex#getFixingCalendar()}.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate fixingDate;
  /**
   * The date of the transfer implied by the fixing date.
   * <p>
   * This is an adjusted date with any business day rule applied.
   * This must be equal to {@link FxIndex#calculateMaturityFromFixing(LocalDate, ReferenceData)}.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate maturityDate;

  //-------------------------------------------------------------------------
  /**
   * Creates an instance from an index and fixing date.
   * <p>
   * The reference data is used to find the maturity date from the fixing date.
   * 
   * @param index  the index
   * @param fixingDate  the fixing date
   * @param refData  the reference data to use when resolving holiday calendars
   * @return the rate observation
   */
  public static FxIndexObservation of(FxIndex index, LocalDate fixingDate, ReferenceData refData) {
    LocalDate maturityDate = index.calculateMaturityFromFixing(fixingDate, refData);
    return new FxIndexObservation(index, fixingDate, maturityDate);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency pair of the FX index.
   * 
   * @return the currency pair of the index
   */
  public CurrencyPair getCurrencyPair() {
    return index.getCurrencyPair();
  }

  //-------------------------------------------------------------------------
  /**
   * Compares this observation to another based on the index and fixing date.
   * <p>
   * The maturity date is ignored.
   * 
   * @param obj  the other observation
   * @return true if equal
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FxIndexObservation other = (FxIndexObservation) obj;
      return index.equals(other.index) && fixingDate.equals(other.fixingDate);
    }
    return false;
  }

  /**
   * Returns a hash code based on the index and fixing date.
   * <p>
   * The maturity date is ignored.
   * 
   * @return the hash code
   */
  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + index.hashCode();
    return hash * 31 + fixingDate.hashCode();
  }

  @Override
  public String toString() {
    return new StringBuilder(64)
        .append("FxIndexObservation[")
        .append(index)
        .append(" on ")
        .append(fixingDate)
        .append(']')
        .toString();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FxIndexObservation}.
   * @return the meta-bean, not null
   */
  public static FxIndexObservation.Meta meta() {
    return FxIndexObservation.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FxIndexObservation.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Creates an instance.
   * @param index  the value of the property, not null
   * @param fixingDate  the value of the property, not null
   * @param maturityDate  the value of the property, not null
   */
  FxIndexObservation(
      FxIndex index,
      LocalDate fixingDate,
      LocalDate maturityDate) {
    JodaBeanUtils.notNull(index, "index");
    JodaBeanUtils.notNull(fixingDate, "fixingDate");
    JodaBeanUtils.notNull(maturityDate, "maturityDate");
    this.index = index;
    this.fixingDate = fixingDate;
    this.maturityDate = maturityDate;
  }

  @Override
  public FxIndexObservation.Meta metaBean() {
    return FxIndexObservation.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the FX index.
   * <p>
   * The rate will be queried from this index.
   * @return the value of the property, not null
   */
  @Override
  public FxIndex getIndex() {
    return index;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the date of the index fixing.
   * <p>
   * This is an adjusted date with any business day rule applied.
   * Valid business days are defined by {@link FxIndex#getFixingCalendar()}.
   * @return the value of the property, not null
   */
  public LocalDate getFixingDate() {
    return fixingDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the date of the transfer implied by the fixing date.
   * <p>
   * This is an adjusted date with any business day rule applied.
   * This must be equal to {@link FxIndex#calculateMaturityFromFixing(LocalDate, ReferenceData)}.
   * @return the value of the property, not null
   */
  public LocalDate getMaturityDate() {
    return maturityDate;
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FxIndexObservation}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code index} property.
     */
    private final MetaProperty<FxIndex> index = DirectMetaProperty.ofImmutable(
        this, "index", FxIndexObservation.class, FxIndex.class);
    /**
     * The meta-property for the {@code fixingDate} property.
     */
    private final MetaProperty<LocalDate> fixingDate = DirectMetaProperty.ofImmutable(
        this, "fixingDate", FxIndexObservation.class, LocalDate.class);
    /**
     * The meta-property for the {@code maturityDate} property.
     */
    private final MetaProperty<LocalDate> maturityDate = DirectMetaProperty.ofImmutable(
        this, "maturityDate", FxIndexObservation.class, LocalDate.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "index",
        "fixingDate",
        "maturityDate");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 100346066:  // index
          return index;
        case 1255202043:  // fixingDate
          return fixingDate;
        case -414641441:  // maturityDate
          return maturityDate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FxIndexObservation> builder() {
      return new FxIndexObservation.Builder();
    }

    @Override
    public Class<? extends FxIndexObservation> beanType() {
      return FxIndexObservation.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code index} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FxIndex> index() {
      return index;
    }

    /**
     * The meta-property for the {@code fixingDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> fixingDate() {
      return fixingDate;
    }

    /**
     * The meta-property for the {@code maturityDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> maturityDate() {
      return maturityDate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 100346066:  // index
          return ((FxIndexObservation) bean).getIndex();
        case 1255202043:  // fixingDate
          return ((FxIndexObservation) bean).getFixingDate();
        case -414641441:  // maturityDate
          return ((FxIndexObservation) bean).getMaturityDate();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code FxIndexObservation}.
   */
  private static final class Builder extends DirectPrivateBeanBuilder<FxIndexObservation> {

    private FxIndex index;
    private LocalDate fixingDate;
    private LocalDate maturityDate;

    /**
     * Restricted constructor.
     */
    private Builder() {
      super(meta());
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 100346066:  // index
          return index;
        case 1255202043:  // fixingDate
          return fixingDate;
        case -414641441:  // maturityDate
          return maturityDate;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 100346066:  // index
          this.index = (FxIndex) newValue;
          break;
        case 1255202043:  // fixingDate
          this.fixingDate = (LocalDate) newValue;
          break;
        case -414641441:  // maturityDate
          this.maturityDate = (LocalDate) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public FxIndexObservation build() {
      return new FxIndexObservation(
          index,
          fixingDate,
          maturityDate);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("FxIndexObservation.Builder{");
      buf.append("index").append('=').append(JodaBeanUtils.toString(index)).append(',').append(' ');
      buf.append("fixingDate").append('=').append(JodaBeanUtils.toString(fixingDate)).append(',').append(' ');
      buf.append("maturityDate").append('=').append(JodaBeanUtils.toString(maturityDate));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
